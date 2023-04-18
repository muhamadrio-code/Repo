package com.riopermana.details

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.CircleCropTransformation
import com.riopermana.details.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class RepoDetailsFragment : Fragment() {

    private val viewModel : RepoDetailsViewModel by viewModels()

    private var _binding : FragmentDetailsBinding? = null
    private val binding : FragmentDetailsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel.resourceFlow
            .onEach {
                it ?: return@onEach

                it.data?.repo?.let { repo ->
                    with(binding) {
                        with(repo){
                            ownerPicture.load(owner?.avatarUrl) {
                                transformations(CircleCropTransformation())
                            }
                            tvOwnerName.text = owner?.login
                            tvRepoFullname.text = fullName
                            tvRepoDescription.text = description
                            btnGithub.setOnClickListener {
                                val intent = Intent(Intent.ACTION_VIEW, htmlUrl?.toUri())
                                startActivity(intent)
                            }
                            tvStarsCount.text = stargazersCount.toString()
                            tvForksCount.text = (forksCount ?: 0).toString()
                            tvWatcherCount.text = (watchersCount ?: 0).toString()
                        }

                        btnBack.setOnClickListener {
                            findNavController().popBackStack()
                        }
                    }
                }
            }
            .launchIn(lifecycleScope)

        return binding.root
    }
}