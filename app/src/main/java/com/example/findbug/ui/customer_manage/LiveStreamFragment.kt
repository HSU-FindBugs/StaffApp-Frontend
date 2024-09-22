package com.example.findbug.ui.customer_manage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentLiveStreamBinding
import com.example.findbug.ui.home.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

class LiveStreamFragment : BaseFragment<FragmentLiveStreamBinding>(R.layout.fragment_live_stream) {

    private val mainViewModel : MainViewModel by activityViewModels()

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        setToolbarNavigation(binding.fragmentLiveStreamToolbar.toolbarPreviousIb)
        initButtons()
        setupWebView()
        //observeLiveStream()
    }

    private fun initButtons() {

    }

    private fun setupWebView() {
        val webView = binding.fragmentLiveStreamPreviewIv
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        webView.loadUrl("http://211.188.52.238:8000/video/2") // 링크 주소 laod
    }

//    // 뷰모델을 통해 서버에서 스트리밍 데이터를 가져옴
//    private fun observeLiveStream() {
//        lifecycleScope.launch {
//            mainViewModel.getLiveStream(2) // 2로 요청
//            mainViewModel.liveStreamResponse.collectLatest { response ->
//                if (response != null && response.isSuccessful) {
//                    response.body()
//                    Log.d("LiveStreamFragment", "res : ${response.body()}")
//                    val inputStream = response.body()?.byteStream()
//                    inputStream?.let {
//                        processStream(it) // 스트림 처리 함수 호출
//                    } ?: Log.e("LiveStreamFragment", "Response body is null")
//                } else {
//                    Log.e("LiveStreamFragment", "res : $response")
//                    Log.e("LiveStreamFragment", "Error receiving live stream data")
//                }
//            }
//        }
//    }
//
//    // 각 프레임(이미지)을 분리하고 처리
//    private fun processStream(inputStream: InputStream) {
//        val boundary = "--frame"
//        val bufferedReader = inputStream.bufferedReader()
//        var line: String?
//
//        while (bufferedReader.readLine().also { line = it } != null) {
//            if (line!!.contains(boundary)) {
//                // Boundary 시작: 다음 부분은 이미지 데이터
//                val imageData = readImageData(bufferedReader)
//                imageData?.let { bytes ->
//                    // 이미지 데이터를 처리해서 UI에 업데이트 (ex: 이미지 뷰에 표시)
//                    val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
//                    updateImageView(bitmap)
//                }
//            }
//        }
//    }
//
//    // 이미지 데이터를 읽어 바이트 배열로 변환
//    private fun readImageData(reader: BufferedReader): ByteArray? {
//        val byteArrayOutputStream = ByteArrayOutputStream()
//        var line: String?
//
//        try {
//            while (reader.readLine().also { line = it } != null && line!!.isNotBlank()) {
//                if (line!!.startsWith("Content-Type: image")) {
//                    // Skip the empty line after the Content-Type
//                    reader.readLine()
//                    // Read the actual image data
//                    var byte = reader.read()
//                    while (byte != -1) {
//                        byteArrayOutputStream.write(byte)
//                        byte = reader.read()
//                    }
//                    break
//                }
//            }
//        } catch (e: IOException) {
//            Log.e("LiveStreamFragment", "Error reading image data: ${e.message}", e)
//            return null
//        }
//
//        return if (byteArrayOutputStream.size() > 0) byteArrayOutputStream.toByteArray() else null
//    }
//
//    // 이미지를 화면에 업데이트하는 함수
//    private fun updateImageView(bitmap: Bitmap) {
//        activity?.runOnUiThread {
//            //binding.fragmentLiveStreamPreviewIv.setImageBitmap(bitmap)
//        }
//    }

}