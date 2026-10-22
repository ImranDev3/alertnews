package com.alertnews.app.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alertnews.app.MusicPlayer
import com.alertnews.app.R
import com.alertnews.app.adapters.TopicAdapter
import com.alertnews.app.data.model.Topic
import com.alertnews.app.data.model.UserSettings
import com.alertnews.app.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var prefs: SharedPreferences
    private lateinit var musicPlayer: MusicPlayer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = requireContext().getSharedPreferences("alertnews_settings", Context.MODE_PRIVATE)
        musicPlayer = MusicPlayer(requireContext())

        loadSettings()

        // Notifications
        binding.switchNotifications.isChecked = prefs.getBoolean("enable_notifications", true)
        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            saveSetting("enable_notifications", isChecked)
        }

        binding.switchHighImpact.isChecked = prefs.getBoolean("high_impact_alerts", true)
        binding.switchHighImpact.setOnCheckedChangeListener { _, isChecked ->
            saveSetting("high_impact_alerts", isChecked)
        }

        // Appearance
        binding.switchDarkMode.isChecked = prefs.getBoolean("enable_dark_mode", true)
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            saveSetting("enable_dark_mode", isChecked)
            Toast.makeText(
                requireContext(),
                "Dark mode requires app restart",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.switchMarketSessions.isChecked = prefs.getBoolean("show_market_sessions", true)
        binding.switchMarketSessions.setOnCheckedChangeListener { _, isChecked ->
            saveSetting("show_market_sessions", isChecked)
        }

        // Music
        binding.switchMusic.isChecked = prefs.getBoolean("enable_music", false)
        binding.switchMusic.setOnCheckedChangeListener { _, isChecked ->
            saveSetting("enable_music", isChecked)
            if (isChecked) {
                startBackgroundMusic()
            } else {
                stopBackgroundMusic()
            }
        }

        binding.seekBarVolume.progress =
            (prefs.getFloat("music_volume", 0.5f) * 100).toInt()
        binding.seekBarVolume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val volume = progress / 100f
                saveSetting("music_volume", volume)
                if (binding.switchMusic.isChecked) {
                    musicPlayer.resumeMusic()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.etMusicUrl.setText(prefs.getString("music_url", ""))

        // Symbols filter
        binding.etSymbols.setText(
            prefs.getStringSet("selected_symbols", emptySet())?.joinToString(",") ?: ""
        )

        // Currencies filter
        binding.etCurrencies.setText(
            prefs.getStringSet("selected_currencies", emptySet())?.joinToString(",") ?: ""
        )

        // Favorites only
        binding.switchFavorites.isChecked = prefs.getBoolean("show_favorites_only", false)
        binding.switchFavorites.setOnCheckedChangeListener { _, isChecked ->
            saveSetting("show_favorites_only", isChecked)
        }

        // Auto refresh
        val interval = prefs.getInt("auto_refresh_interval", 15)
        binding.etAutoRefresh.setText(interval.toString())

        // Save filters button
        binding.btnSaveFilters.setOnClickListener {
            saveSymbolFilter()
            saveCurrencyFilter()
            saveAutoRefresh()
            Toast.makeText(requireContext(), "Filters saved", Toast.LENGTH_SHORT).show()
        }

        // Clear cache button
        binding.btnClearCache.setOnClickListener {
            val dao = com.alertnews.app.data.local.NewsDatabase.create(requireContext()).newsDao()
            Thread {
                dao.deleteOldNews(System.currentTimeMillis() - 86400000)
            }.start()
            Toast.makeText(requireContext(), "Cache cleared", Toast.LENGTH_SHORT).show()
        }

        // Topic filtering A-Z
        setupTopicList()

        // Timezone
        setupTimezoneSelector()
    }

    private fun loadSettings() {
        val settings = prefs.edit()
        // Ensure defaults exist
        if (!prefs.contains("enable_notifications")) {
            settings.putBoolean("enable_notifications", true)
            settings.putBoolean("high_impact_alerts", true)
            settings.putBoolean("enable_music", false)
            settings.putFloat("music_volume", 0.5f)
            settings.putString("music_url", "")
            settings.putBoolean("enable_dark_mode", true)
            settings.putBoolean("show_market_sessions", true)
            settings.putBoolean("show_favorites_only", false)
            settings.putInt("auto_refresh_interval", 15)
            settings.apply()
        }
    }

    private fun <T> saveSetting(key: String, value: T) {
        val edit = prefs.edit()
        when (value) {
            is Boolean -> edit.putBoolean(key, value)
            is Float -> edit.putFloat(key, value)
            is Int -> edit.putInt(key, value)
            is String -> edit.putString(key, value)
            is Set<*> -> @Suppress("UNCHECKED_CAST") edit.putStringSet(key, value as Set<String>)
        }
        edit.apply()
    }

    private fun startBackgroundMusic() {
        val url = prefs.getString("music_url", "")
        if (url.isNotBlank()) {
            musicPlayer.playMusic(url) { playing ->
                if (playing) {
                    binding.switchMusic.text = "Music: Playing"
                }
            }
        } else {
            Toast.makeText(requireContext(), "Enter a valid music URL", Toast.LENGTH_SHORT).show()
            binding.switchMusic.isChecked = false
        }
    }

    private fun stopBackgroundMusic() {
        musicPlayer.stopMusic { }
        binding.switchMusic.text = "Music: Off"
    }

    private fun saveSymbolFilter() {
        val symbolsText = binding.etSymbols.text.toString().trim()
        val symbols = if (symbolsText.isNotBlank()) {
            symbolsText.split(",").map { it.trim() }.filter { it.isNotBlank() }.toSet()
        } else {
            emptySet()
        }
        saveSetting("selected_symbols", symbols)
    }

    private fun saveCurrencyFilter() {
        val currenciesText = binding.etCurrencies.text.toString().trim()
        val currencies = if (currenciesText.isNotBlank()) {
            currenciesText.split(",").map { it.trim() }.filter { it.isNotBlank() }.toSet()
        } else {
            emptySet()
        }
        saveSetting("selected_currencies", currencies)
    }

    private fun saveAutoRefresh() {
        val interval = binding.etAutoRefresh.text.toString().toIntOrNull() ?: 15
        saveSetting("auto_refresh_interval", interval)
    }

    private fun setupTopicList() {
        val topicAdapter = TopicAdapter(Topic.TOPICS) { topic, isSelected ->
            val currentSet = prefs.getStringSet("topic_alerts", emptySet())?.toMutableSet() ?: mutableSetOf()
            if (isSelected) currentSet.add(topic.name) else currentSet.remove(topic.name)
            saveSetting("topic_alerts", currentSet)
        }

        val savedTopics = prefs.getStringSet("topic_alerts", emptySet())
        topicAdapter.setSelectedTopics(savedTopics ?: emptySet())

        binding.topicRecycler.adapter = topicAdapter
        binding.topicRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupTimezoneSelector() {
        val timezones = listOf(
            "UTC", "America/New_York", "America/Chicago", "America/Denver",
            "America/Los_Angeles", "Europe/London", "Europe/Paris", "Europe/Berlin",
            "Asia/Tokyo", "Asia/Hong_Kong", "Asia/Dubai", "Australia/Sydney"
        )

        val currentTz = prefs.getString("timezone", "UTC")
        val tzIndex = timezones.indexOf(currentTz)

        binding.spinnerTimezone.setSelection(if (tzIndex >= 0) tzIndex else 0)
        binding.spinnerTimezone.setSelection(tzIndex.coerceAtLeast(0), false)

        // Populate spinner
        val adapter = android.widget.ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            timezones
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.spinnerTimezone.adapter = adapter
        binding.spinnerTimezone.setSelection(if (tzIndex >= 0) tzIndex else 0)

        binding.spinnerTimezone.onItemSelectedListener =
            object : android.widget.AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: android.widget.AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    saveSetting("timezone", timezones[position])
                }

                override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {}
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}