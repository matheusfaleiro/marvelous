package dev.theuzfaleiro.marvelous.presentation.heroes.adapter

import androidx.recyclerview.widget.DiffUtil
import dev.theuzfaleiro.network.domain.model.Hero

class HeroDiffCallback : DiffUtil.ItemCallback<Hero>() {
    override fun areItemsTheSame(oldItem: Hero, newItem: Hero) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean =
        oldItem == newItem
}