package dev.theuzfaleiro.marvelous.presentation.heroes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import dev.theuzfaleiro.marvelous.databinding.ItemHeroBinding
import dev.theuzfaleiro.marvelous.databinding.ItemLoadingErrorBinding

class LoadMoreViewHolder(
    private val loadMoreBinding: ItemLoadingErrorBinding,
    private val onRetryClicked: () -> Unit
) : RecyclerView.ViewHolder(loadMoreBinding.root) {

    private val binding = ItemLoadingErrorBinding.bind(itemView)

    private val progressBar = binding.progressBarHeroLoading
    private val errorMessage = binding.textViewErrorMessage
    private val retryButton = binding.buttonTryAgain.also {
        it.setOnClickListener { onRetryClicked() }
    }


    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            progressBar.visibility = View.GONE
            errorMessage.text = loadState.error.localizedMessage
            retryButton.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.VISIBLE
            errorMessage.visibility = View.GONE
            retryButton.visibility = View.GONE

        }
    }

    companion object {
        fun create(parent: ViewGroup, onRetryClicked: () -> Unit): LoadMoreViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemLoadingErrorBinding = ItemLoadingErrorBinding.inflate(inflater, parent, false)

            return LoadMoreViewHolder(itemLoadingErrorBinding, onRetryClicked)
        }
    }
}