package com.riopermana.core.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
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
                repoFullname.text = item.repo.fullName
                favoriteBtn.isChecked = item.isFavorite
                ownerPicture.load(owner?.avatarUrl){
                    transformations(CircleCropTransformation())
                }
                ownerName.text = owner?.login
                repoDescription.text = repo.description
                repoLanguage.text = repo.language
                starCount.text = repo.stargazersCount.toString()

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