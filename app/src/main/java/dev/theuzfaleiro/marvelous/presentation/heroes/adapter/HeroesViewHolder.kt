package dev.theuzfaleiro.marvelous.presentation.heroes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.theuzfaleiro.marvelous.R
import dev.theuzfaleiro.marvelous.databinding.ItemHeroBinding
import dev.theuzfaleiro.network.domain.model.Hero

class HeroesViewHolder(itemHeroesBinding: ItemHeroBinding) :
    RecyclerView.ViewHolder(itemHeroesBinding.root) {
    private val heroName = itemHeroesBinding.textViewHeroName
    private val heroImage = itemHeroesBinding.imageViewHero

    fun bind(hero: Hero) {
        heroName.text = hero.name
        heroImage.load(hero.imageUrl) {
            crossfade(true)
            fallback(R.drawable.ic_marvel_logo)
            placeholder(R.drawable.ic_marvel_logo)
        }
    }

    companion object {
        fun create(parent: ViewGroup): HeroesViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemHeroBinding = ItemHeroBinding.inflate(inflater, parent, false)

            return HeroesViewHolder(itemHeroBinding)
        }
    }
}
