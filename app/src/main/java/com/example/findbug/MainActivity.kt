package com.example.findbug

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun setLayout() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) // 다크모드 비활성화
        initSetting()
    }

    private fun initSetting() {
        setNavigation()
    }

    // 외부 터치시 키보드 숨기기, 포커스 제거
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        if (currentFocus is EditText) {
            currentFocus!!.clearFocus()
        }
        return super.dispatchTouchEvent(ev)
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