package dev.theuzfaleiro.marvelous.presentation.heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dev.theuzfaleiro.marvelous.R
import dev.theuzfaleiro.marvelous.databinding.FragmentHeroesBinding
import dev.theuzfaleiro.marvelous.presentation.heroes.adapter.HeroesAdapter
import dev.theuzfaleiro.network.domain.model.Hero

@AndroidEntryPoint
class HeroesFragment : Fragment() {
    private var heroesBinding: FragmentHeroesBinding? = null

    private val binding get() = heroesBinding!!

    private val heroesAdapter by lazy {
        HeroesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHeroesBinding.inflate(inflater, container, false).also { heroesBinding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpHeroesAdapter()

        heroesAdapter.submitList(
            listOf(
                Hero(name = "Spider Man", imageUrl = ""),
                Hero(name = "Spider Man", imageUrl = "")
            )
        )
    }

    private fun setUpHeroesAdapter() {
        with(binding.recyclerViewHeroes) {
            setHasFixedSize(true)
            adapter = heroesAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        heroesBinding = null
    }
}
