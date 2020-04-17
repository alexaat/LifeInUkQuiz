package com.alexaat.llfeinukquiz


import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.alexaat.llfeinukquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout:DrawerLayout
    private lateinit var appBarConfiguration : AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        if (application.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.linearLayoutMainActivity.setBackgroundResource(R.drawable.uk_flag_horisontal)
        } else if (application.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.linearLayoutMainActivity.setBackgroundResource(R.drawable.uk_flag_vertical)
        }

        drawerLayout = binding.drawerLayout

        val navController = this.findNavController(R.id.nav_host)
        NavigationUI.setupActionBarWithNavController(this, navController,drawerLayout)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupWithNavController(binding.navDrawer,navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.titleFragment) {
                binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
             binding.linearLayoutMainActivity.setBackgroundResource(R.drawable.uk_flag_horisontal)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.linearLayoutMainActivity.setBackgroundResource(R.drawable.uk_flag_vertical)
        }

    }
}
