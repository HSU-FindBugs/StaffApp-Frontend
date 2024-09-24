package com.example.findbug

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.findbug.base.BaseActivity
import com.example.findbug.databinding.ActivityMainBinding
import com.example.findbug.domain.model.response.BugDetectionAlertResponse
import com.example.findbug.ui.home.MainViewModel
import com.example.findbug.utils.extension.navigateToTopLevelDestination
import com.example.findbug.utils.sse.SSEClient
import com.example.findbug.utils.sse.SSEHandler
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main), SSEHandler {

    private lateinit var navController: NavController

    private val mainViewModel: MainViewModel by viewModels()
    private val sseClient = SSEClient()
    private val _sseEventFlow = MutableStateFlow<BugDetectionAlertResponse?>(null)
    val sseEventFlow = _sseEventFlow.asStateFlow()
    private val gson = Gson()

    override fun setLayout() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) // 다크모드 비활성화
        initSetting()
    }

    private fun initSetting() {
        setNavigation()

        //lifecycleScope.launch { mainViewModel.notificationConnect(1) }
        //initSseConnection()
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

    private fun initSseConnection() {
        sseClient.initSse(1, this) { throwable ->
            Log.e("SSE Error", throwable.message ?: "Unknown error")
        }
    }

    // SSEHandler의 구현
    override fun onSSEConnectionOpened() {
        Log.d("SSE1111111", "SSE connection opened")
    }

    override fun onSSEConnectionClosed() {
        Log.d("SSE1111111", "SSE connection closed")
    }

    override fun onSSEEventReceived(event: String, messageEvent: String) {
        Log.d("SSE1111111", "Event: $event, Data: $messageEvent")
        try {
            val alertResponse = gson.fromJson(messageEvent, BugDetectionAlertResponse::class.java)
            mainViewModel.addBugDetectionAlert(alertResponse)
        } catch (e: Exception) {
            Log.e("SSE1111111", "Error parsing SSE data: ${e.message}")
        }
    }

    override fun onSSEError(t: Throwable) {
        Log.e("SSE1111111", "Error: ${t.message}")
    }

    fun getSseEventData(): BugDetectionAlertResponse {
        val bugDetectionAlertResponse = mainViewModel.bugDetectionAlertResponse.value
        return bugDetectionAlertResponse
    }

    override fun onDestroy() {
        super.onDestroy()
        sseClient.disconnect()
    }

}