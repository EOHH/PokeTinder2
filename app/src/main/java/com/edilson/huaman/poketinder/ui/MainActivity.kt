package com.edilson.huaman.poketinder.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.edilson.huaman.poketinder.R
import com.edilson.huaman.poketinder.databinding.ActivityMainBinding
import com.edilson.huaman.poketinder.ui.BaseActivity
import com.edilson.huaman.poketinder.ui.viewmodel.MainViewModel


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

        private val viewModel by lazy { MainViewModel() }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val navView: BottomNavigationView = binding.navView

            val navController = findNavController(R.id.nav_host_fragment)

            navView.setupWithNavController(navController)
        }
    }