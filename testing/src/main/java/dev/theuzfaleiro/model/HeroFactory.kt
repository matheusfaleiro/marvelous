package dev.theuzfaleiro.model

import dev.theuzfaleiro.network.domain.model.Hero


sealed class DefaultHero {
    object Batman : DefaultHero()
    object Superman : DefaultHero()
    object SpiderMan : DefaultHero()
}

class HeroFactory {
    fun createHero(hero: DefaultHero): Hero {
        return when (hero) {
            DefaultHero.Batman -> Hero(
                name = "Batman",
                imageUrl = "https://i.annihil.us/u/prod/marvel/i/mg/9/30/538cd33e15ab7/standard_xlarge.jpg"
            )
            DefaultHero.Superman -> Hero(
                name = "Superman",
                imageUrl = "https://i.annihil.us/u/prod/marvel/i/mg/9/30/538cd33e15ab7/standard_xlarge.jpg"
            )
            DefaultHero.SpiderMan -> Hero(
                name = "Spider-Man",
                imageUrl = "https://i.annihil.us/u/prod/marvel/i/mg/9/30/538cd33e15ab7/standard_xlarge.jpg"
            )
        }
    }
}