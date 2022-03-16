package dev.theuzfaleiro.marvelous.presentation.heroes.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import dev.theuzfaleiro.network.domain.model.Hero

class LoadMoreAdapter(private val onRetryClicked: () -> Unit) :
    LoadStateAdapter<LoadMoreViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = LoadMoreViewHolder.create(parent, onRetryClicked)

    override fun onBindViewHolder(
        holder: LoadMoreViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)
}