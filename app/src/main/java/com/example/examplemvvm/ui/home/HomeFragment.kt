package com.example.examplemvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examplemvvm.R
import com.example.examplemvvm.databinding.HomeFragmentBinding
import com.example.examplemvvm.ui.form.FormFragment
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class HomeFragment : Fragment(), KoinComponent {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding: HomeFragmentBinding
    private val viewModel: HomeViewModel by inject()
    private val homeGuestAdapter: HomeGuestAdapter by lazy { HomeGuestAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        binding.fab.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, FormFragment.newInstance()).commitNow()
        }

        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is HomeViewState.ListLoading -> {
                    binding.progressBar.isVisible = true
                }
                is HomeViewState.ListLoaded -> {
                    binding.progressBar.isVisible = false
                    homeGuestAdapter.differ.submitList(viewState.list)
                }
                is HomeViewState.ListNotLoaded -> {
                    binding.progressBar.isVisible = false
                    viewModel.loadGuests()
                }
                else -> Toast.makeText(requireContext(), "Error on load list", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun setUpRecyclerView()
    {
        with(binding.recyclerGuest)
        {
            adapter = homeGuestAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }
}