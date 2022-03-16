package dev.theuzfaleiro.marvelous.presentation.heroes.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import dev.theuzfaleiro.network.domain.model.Hero

class HeroesAdapter : PagingDataAdapter<Hero, HeroesViewHolder>(HeroDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HeroesViewHolder.create(parent)

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}