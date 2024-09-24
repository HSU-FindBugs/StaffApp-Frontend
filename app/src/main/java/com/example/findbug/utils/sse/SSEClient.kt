package com.example.findbug.utils.sse

import android.util.Log
import com.example.findbug.domain.model.response.BugDetectionAlertResponse
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import java.util.concurrent.TimeUnit

interface SSEDataListener {
    fun onDataReceived(data: String)
}


class SSEClient(private val listener: SSEDataListener) {
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)  // 연결 타임아웃
        .readTimeout(3600, TimeUnit.SECONDS)    // 읽기 타임아웃
        .build()

    private var eventSource: EventSource? = null

    fun connectToSSE(staffId: Long) {
        // 서버의 SSE 엔드포인트
        val url = "http://findbug.kro.kr:8079/detection-events/connect/$staffId"

        // SSE 요청을 만들고 EventSource로 SSE 메시지를 수신함
        val request = Request.Builder()
            .url(url)
            .build()

        // EventSource로 SSE 이벤트를 처리할 수 있게 해줌
        val factory = EventSources.createFactory(client)
        factory.newEventSource(request, object : EventSourceListener() {
            override fun onOpen(eventSource: EventSource, response: Response) {
                Log.d("SSE수신","SSE 연결 성공")
            }

            override fun onEvent(eventSource: EventSource, id: String?, type: String?, data: String) {
                Log.d("SSE수신","SSE onEvent")
                if (data.isEmpty()) {
                    Log.e("SSE수신", "onEvent: Received empty data")
                } else {
                    try {
                        listener.onDataReceived(data)
                    } catch (e: JsonSyntaxException) {
                        Log.e("SSE수신", "Failed to parse data to BugDetectionAlertResponse: ${e.message}")
                    }
                }
            }

            override fun onClosed(eventSource: EventSource) {
                Log.d("SSE수신","SSE onClosed")
            }

            override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
                Log.e("SSE수신", "SSE 연결 실패: ${t?.message}")
                if (response != null) {
                    Log.e("SSE수신", "Response, code: ${response.code}")
                }
                super.onFailure(eventSource, t, response)
            }

        })
    }

}
