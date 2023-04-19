package com.riopermana.core.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.riopermana.core.databinding.RepoItemLayoutBinding
import com.riopermana.core.model.FavoriteAbleRepo

class FavoriteAbleRepoAdapter :
    ListAdapter<FavoriteAbleRepo, FavoriteAbleRepoAdapter.FavoriteAbleRepoViewHolder>(
        FavoriteAbleRepoDiffUtil()
    ) {

    private var toggleFavoriteClickListener: ((FavoriteAbleRepo) -> Unit)? = null
    private var onItemClickListener: ((Int) -> Unit)? = null

    inner class FavoriteAbleRepoViewHolder(private val binding: RepoItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FavoriteAbleRepo) {
            val repo = item.repo
            val owner = repo.owner
            with(binding){
                binding.root.layoutParams = RecyclerView.LayoutParams(
                    RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT
                )
                tvRepoFullname.text = item.repo.fullName
                favoriteBtn.isChecked = item.isFavorite
                ownerPicture.load(owner?.avatarUrl){
                    transformations(CircleCropTransformation())
                }
                tvOwnerName.text = owner?.login
                tvRepoDescription.text = repo.description
                repoLanguage.isVisible = !repo.language.isNullOrEmpty()
                repoLanguage.text = repo.language
                tvStarCount.text = repo.stargazersCount.toString()

                root.setOnClickListener {
                    onItemClickListener?.invoke(item.repo.id)
                }

                favoriteBtn.setOnClickListener {
                    toggleFavoriteClickListener?.invoke(
                        item.copy(
                            repo = item.repo,
                            isFavorite = !item.isFavorite
                        )
                    )
                }
            }
        }
    }

    fun setToggleFavoriteClickListener(listener: (FavoriteAbleRepo) -> Unit) {
        toggleFavoriteClickListener = listener
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAbleRepoViewHolder {
        return FavoriteAbleRepoViewHolder(
            RepoItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoriteAbleRepoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class FavoriteAbleRepoDiffUtil : DiffUtil.ItemCallback<FavoriteAbleRepo>() {
    override fun areItemsTheSame(oldItem: FavoriteAbleRepo, newItem: FavoriteAbleRepo): Boolean {
        return oldItem.repo.id == newItem.repo.id
    }

    override fun areContentsTheSame(oldItem: FavoriteAbleRepo, newItem: FavoriteAbleRepo): Boolean {
        return oldItem == newItem
    }
}