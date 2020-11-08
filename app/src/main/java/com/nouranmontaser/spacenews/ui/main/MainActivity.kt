package com.nouranmontaser.spacenews.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.nouranmontaser.spacenews.R
import com.nouranmontaser.spacenews.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        setUpUi()
    }

    private fun setUpUi() {
        Navigation.findNavController(this, R.id.nav_host_fragment)//TODO
    }

    override fun onSupportNavigateUp(): Boolean {
        val navigationController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return navigationController.navigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val navigationController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navigationController.navigateUp()
    }
}