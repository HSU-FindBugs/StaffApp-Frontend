package com.example.findbug

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.findbug.base.BaseActivity
import com.example.findbug.databinding.ActivityMainBinding
import com.example.findbug.utils.extension.navigateToTopLevelDestination

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun setLayout() {
        // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) // 다크모드 해제
        initSetting()
    }

    private fun initSetting() {
        setNavigation()
    }

    private fun setNavigation() {
        val mainBottomNavigationBar = binding.mainBottomNavigationBar
        mainBottomNavigationBar.itemIconTintList = null

        val host = supportFragmentManager.findFragmentById(binding.mainNavHostFragment.id) as NavHostFragment ?: return
        navController = host.navController
        mainBottomNavigationBar.apply {
            setupWithNavController(navController)
        }

        // 최상위 프래그먼트 이동 (홈, 고객관리, 알림, 프로필)
        mainBottomNavigationBar.setOnItemSelectedListener { item ->
            when(item.itemId) {

                R.id.homeFragment -> {
                    navController.navigateToTopLevelDestination(
                        R.id.homeFragment,
                        navController
                    )
                }

                R.id.customerHomeFragment -> {
                    navController.navigateToTopLevelDestination(
                        R.id.customerHomeFragment,
                        navController
                    )
                }

                R.id.notificationHomeFragment -> {
                    navController.navigateToTopLevelDestination(
                        R.id.notificationHomeFragment,
                        navController
                    )
                }

                R.id.profileHomeFragment -> {
                    navController.navigateToTopLevelDestination(
                        R.id.profileHomeFragment,
                        navController
                    )
                }
                else -> false
            }
        }
    }

}