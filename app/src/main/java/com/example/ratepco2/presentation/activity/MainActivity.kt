package com.example.ratepco2.presentation.activity

import android.content.res.Configuration
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.ratepco2.R
import com.example.ratepco2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setProps()
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        supportActionBar?.setDisplayShowTitleEnabled(false);
    }

    override fun onStart() {
        super.onStart()
        colorBackground()
    }

    private fun setProps() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        navHostFragment = getNavHostFragment()
        navController = getNavController()
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun getNavHostFragment(): NavHostFragment = supportFragmentManager
        .findFragmentById(binding.navHostFragmentContainer.id) as NavHostFragment

    private fun getNavController(): NavController = navHostFragment.navController

    fun colorBackground(isBackgroundPrimary: Boolean = false) {
        val window: Window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (isBackgroundPrimary) {
            window.decorView.systemUiVisibility = 0
            window.statusBarColor = ContextCompat.getColor(this, R.color.primaryDarkColor)
            binding.rootLayout.background = ContextCompat.getColor(this, R.color.primaryDarkColor).toDrawable()
            binding.toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.ADD)
            binding.tvTitle.setTextColor(ContextCompat.getColor(this, R.color.white))
            colorToolBar(R.color.primaryDarkColor)
        }
        else if (resources.configuration.uiMode != 33) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
            binding.tvTitle.setTextColor(ContextCompat.getColor(this, R.color.primaryDarkColor))
            binding.rootLayout.background = ContextCompat.getColor(this, R.color.white).toDrawable()
            binding.toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.black), PorterDuff.Mode.ADD)
            colorToolBar(R.color.white)
        } else {
            window.decorView.systemUiVisibility = 0
            window.statusBarColor = ContextCompat.getColor(this, R.color.black)
            binding.toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.ADD)
            binding.tvTitle.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.rootLayout.background = ContextCompat.getColor(this, R.color.black).toDrawable()
            colorToolBar(R.color.black)
        }
    }

    private fun colorToolBar(color: Int) {
        binding.toolbar.background = ContextCompat.getColor(this, color).toDrawable()
    }

    fun hideToolbar() = this.supportActionBar?.hide()

    fun showToolbar() = this.supportActionBar?.show()

    fun addEmission(emission: Double) {
        var partial = binding.tvPartialResult.text.toString().toDouble()
        partial += emission
        binding.tvPartialResult.text = partial.toString()
    }

    fun divideEmission(peopleQtt: Int) {
        var partial = binding.tvPartialResult.text.toString().toDouble()
        partial /= peopleQtt
        binding.tvPartialResult.text = partial.toString()
    }
}