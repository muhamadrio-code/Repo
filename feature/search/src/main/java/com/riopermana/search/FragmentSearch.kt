package com.riopermana.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.riopermana.core.data.ResourceState
import com.riopermana.core.presentation.FavoriteAbleRepoAdapter
import com.riopermana.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentSearch : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: FavoriteAbleRepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        setupRecyclerView()
        subscribeCollector()
        setupSearchView()
        return binding.root
    }

    private fun subscribeCollector() {
        lifecycleScope.launch {
            viewModel.resourceStateFlow.collect { resource ->
                resource ?: return@collect
                adapter.submitList(resource.data)
                showLoading(resource.state is ResourceState.Loading)
            }
        }
    }

    private fun showLoading(isVisible: Boolean) {
        binding.loadingIndicator.isVisible = isVisible
    }

    private fun setupSearchView() {
        binding.searchBar.setOnQueryTextListener(this)
    }

    private fun setupRecyclerView() {
        adapter = FavoriteAbleRepoAdapter()
        adapter.setToggleFavoriteClickListener {
            viewModel.toggleFavoriteRepo(it)
        }
        val itemDecorator = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.repoList.addItemDecoration(itemDecorator)
        binding.repoList.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query ?: return false
        viewModel.getRepos(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}