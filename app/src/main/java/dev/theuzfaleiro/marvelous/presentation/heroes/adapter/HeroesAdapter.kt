package dev.theuzfaleiro.marvelous.presentation.heroes.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import dev.theuzfaleiro.network.domain.model.Hero

class HeroesAdapter : ListAdapter<Hero, HeroesViewHolder>(HeroDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HeroesViewHolder.create(parent)

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}