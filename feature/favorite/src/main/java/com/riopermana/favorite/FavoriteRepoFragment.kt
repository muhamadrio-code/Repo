package com.riopermana.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.riopermana.core.data.ResourceState
import com.riopermana.core.presentation.FavoriteAbleRepoAdapter
import com.riopermana.favorite.databinding.FragmentFavoriteBinding
import com.riopermana.favorite.di.inject
import kotlinx.coroutines.launch
import javax.inject.Inject


class FavoriteRepoFragment : Fragment() {

    @Inject
    lateinit var favoriteFeatureViewModelFactory: FavoriteFeatureViewModelFactory

    private val viewModel: FavoriteRepoViewModel by viewModels { favoriteFeatureViewModelFactory }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding get() = _binding!!
    private lateinit var adapter: FavoriteAbleRepoAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        setupRecyclerView()
        subscribeObserver()
        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = FavoriteAbleRepoAdapter()
        binding.favoriteRepoList.adapter = adapter
        adapter.setToggleFavoriteClickListener {
            viewModel.toggleFavoriteRepo(it)
        }
        adapter.setOnItemClickListener { repoId ->
            val request = NavDeepLinkRequest.Builder
                .fromUri(
                    getString(com.riopermana.core.R.string.repo_details_deeplink).replace(
                        "{repoId}",
                        repoId.toString()
                    ).toUri()
                ).build()
            requireParentFragment().requireParentFragment().findNavController().navigate(request)
        }
    }

    private fun subscribeObserver() {
        lifecycleScope.launch {
            viewModel.favoriteUserResource.collect { resource ->
                adapter.submitList(resource.data)
            }
        }
    }


}