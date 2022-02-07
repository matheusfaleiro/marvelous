package dev.theuzfaleiro.marvelous.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.theuzfaleiro.marvelous.R
import dev.theuzfaleiro.marvelous.databinding.ActivityHomeBinding

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController
    private lateinit var applicationBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        navigationController = navHostFragment.navController

        homeBinding.bottomNavigationView.setupWithNavController(navigationController)

        applicationBarConfiguration = AppBarConfiguration(
            homeBinding.bottomNavigationView.menu
        )

        homeBinding.toolbar.setupWithNavController(
            navigationController,
            applicationBarConfiguration
        )
    }
}
