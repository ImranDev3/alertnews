package com.alertnews.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alertnews.app.R
import com.alertnews.app.adapters.CalendarAdapter
import com.alertnews.app.viewmodels.NewsViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.alertnews.app.databinding.FragmentCalendarBinding
import androidx.recyclerview.widget.LinearLayoutManager

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: CalendarAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]

        adapter = CalendarAdapter()
        binding.calendarRecycler.adapter = adapter
        binding.calendarRecycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.calendarEvents.observe(viewLifecycleOwner) { events ->
            adapter.submitList(events)
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshCalendar()
            viewModel.refreshIpo()
        }

        // Load calendar data
        viewModel.refreshCalendar()
    }
}