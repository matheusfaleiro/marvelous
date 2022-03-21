package dev.theuzfaleiro.marvelous.presentation.heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import dev.theuzfaleiro.marvelous.databinding.FragmentHeroesBinding
import dev.theuzfaleiro.marvelous.presentation.heroes.adapter.HeroesAdapter
import dev.theuzfaleiro.marvelous.presentation.heroes.adapter.LoadMoreAdapter
import dev.theuzfaleiro.marvelous.presentation.heroes.viewmodel.HeroViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val FLIPPER_POSITION_LOADING = 0
private const val FLIPPER_POSITION_HEROES = 1
private const val FLIPPER_POSITION_ERROR = 2

@AndroidEntryPoint
class HeroesFragment : Fragment() {
    private var heroesBinding: FragmentHeroesBinding? = null

    private val binding get() = heroesBinding!!

    private val heroViewModel: HeroViewModel by viewModels()

    private lateinit var heroesAdapter: HeroesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHeroesBinding.inflate(inflater, container, false).also { heroesBinding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpHeroesAdapter()

        observeLoadingState()

        getCharactersData()
    }

    private fun setUpHeroesAdapter() {
        heroesAdapter = HeroesAdapter()

        with(binding.recyclerViewHeroes) {
            setHasFixedSize(true)
            adapter = heroesAdapter.withLoadStateFooter(
                footer = LoadMoreAdapter(heroesAdapter::retry)
            )
        }
    }

    private fun setUpRetryButton() {
        binding.includeLoadingError.buttonRetry.setOnClickListener { heroesAdapter.retry() }
    }

    private fun observeLoadingState() = lifecycleScope.launch {
        heroesAdapter.loadStateFlow.collectLatest { loadState ->
            binding.viewFlipperHeroes.displayedChild = when (loadState.refresh) {
                is LoadState.Loading -> {
                    setLoadingVisibility(true)
                    FLIPPER_POSITION_LOADING
                }
                is LoadState.Error -> {
                    setLoadingVisibility(false)
                    setUpRetryButton()
                    FLIPPER_POSITION_ERROR
                }
                is LoadState.NotLoading -> {
                    setLoadingVisibility(false)
                    FLIPPER_POSITION_HEROES
                }
                else -> {
                    FLIPPER_POSITION_ERROR
                }
            }
        }
    }

    private fun getCharactersData() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                heroViewModel.fetchCharacters().collect { pagingData ->
                    heroesAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun setLoadingVisibility(isShown: Boolean) {
        binding.includeHeroLoading.shimmerFrameLayout.run {
            isVisible = isShown
            if (isShown) {
                startShimmer()
            } else {
                stopShimmer()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        heroesBinding = null
    }
}
