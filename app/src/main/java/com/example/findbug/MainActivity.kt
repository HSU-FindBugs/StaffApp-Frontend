package com.example.findbug

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.findbug.base.BaseActivity
import com.example.findbug.databinding.ActivityMainBinding
import com.example.findbug.domain.model.response.BugDetectionAlertResponse
import com.example.findbug.ui.home.MainViewModel
import com.example.findbug.utils.extension.navigateToTopLevelDestination
import com.example.findbug.utils.sse.SSEClient
import com.example.findbug.utils.sse.SSEDataListener
import android.Manifest
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main), SSEDataListener {

    private lateinit var navController: NavController

    private val NOTIFICATION_PERMISSION_REQUEST_CODE = 1001
    private val mainViewModel: MainViewModel by viewModels()
    private val sseClient = SSEClient(this)

    override fun setLayout() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) // 다크모드 비활성화
        initSetting()
    }

    private fun initSseConnection() { sseClient.connectToSSE(1L) }  // 네트워크 작업 실행

    private fun initSetting() {
        checkNotificationPermission()
        setNavigation()
        initSseConnection()
    }

    override fun onDataReceived(data: String) {
        Log.d("SSE수신", "Received data in MainActivity: $data")

        val cleanData = data.removeSurrounding("\"")
        var alertResponse: BugDetectionAlertResponse = BugDetectionAlertResponse()

        if (!data.equals("연결!")) {
             alertResponse = try {
                Gson().fromJson(cleanData, BugDetectionAlertResponse::class.java)
            } catch (e: Exception) {
                return
            }
        }

        // 각 값 꺼내기
        val name = alertResponse.name
        val address = alertResponse.address
        val recentFindTime = alertResponse.recentFindTime

        // 주소 세부정보 꺼내기
        val region1 = address?.region_1depth
        val region2 = address?.region_2depth
        val region3 = address?.region_3depth
        val streetName = address?.street_name
        val detailAddress = address?.detail_address

        val fullAddress = buildString {
            if (!region1.isNullOrEmpty()) append("$region1 ")
            if (!region2.isNullOrEmpty()) append("$region2 ")
            if (!region3.isNullOrEmpty()) append("$region3 ")
        }.trim()

        mainViewModel.addBugDetectionAlert(alertResponse)

        val name1 = mainViewModel.name.value.takeIf { !it.isNullOrEmpty() } ?: "박종범"
        val fullAddress1 = mainViewModel.fullAddress.value.takeIf { !it.isNullOrEmpty() } ?: "한성대학교"

        sendNotification(name1, fullAddress1)
    }

    // 서버 채널 생성
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "500"
            val channelName = "MainChannel"
            val channelDescription = "Test Notifications"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val notificationChannel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
                enableLights(true)
                enableVibration(true)
                setShowBadge(true) // 배지 표시 활성화
            }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    // 알림 전송
    private fun sendNotification(title: String, body: String) {
        createNotificationChannel()

        val channelId = "500"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_title_logo)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setNumber(1) // 배지에 표시할 숫자 설정

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notificationBuilder.build())
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

    // 네비게이션 설정 함수
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

    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // 권한이 없으면 요청
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), NOTIFICATION_PERMISSION_REQUEST_CODE)
            }
        }
    }

    // 권한 요청 결과 처리
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            NOTIFICATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("알림권한", "알림 권한이 허용되었습니다.")
                } else {
                    Log.d("알림권한", "알림 권한이 거부되었습니다.")
                }
            }
        }
    }

}