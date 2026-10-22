package com.alertnews.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.alertnews.app.R
import com.alertnews.app.adapters.IpoAdapter
import com.alertnews.app.viewmodels.NewsViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.alertnews.app.databinding.FragmentIpoBinding

class IpoFragment : Fragment() {

    private lateinit var binding: FragmentIpoBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: IpoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIpoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]

        adapter = IpoAdapter()
        binding.ipoRecycler.adapter = adapter
        binding.ipoRecycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.ipoEvents.observe(viewLifecycleOwner) { events ->
            adapter.submitList(events)
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshIpo()
        }

        viewModel.refreshIpo()
    }
}