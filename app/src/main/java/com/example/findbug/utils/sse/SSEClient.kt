package com.example.findbug.utils.sse

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.Response
import okhttp3.sse.EventSources
import java.util.concurrent.TimeUnit

interface SSEHandler {
    fun onSSEConnectionOpened()
    fun onSSEConnectionClosed()
    fun onSSEEventReceived(event: String, messageEvent: String)
    fun onSSEError(t: Throwable)
}

class SSEClient {

    private var sseHandler: SSEHandler? = null
    private var eventSource: EventSource? = null
    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(0, TimeUnit.SECONDS)
        .build()

    fun initSse(staffId: Long, sseHandler: SSEHandler, errorCallback: (Throwable) -> Unit) {
        this.sseHandler = sseHandler
        val request = Request.Builder()
            .url("http://findbug.kro.kr:8079/detection-events/connect/$staffId")
            .build()

        try {
            eventSource = EventSources.createFactory(client).newEventSource(request, object : EventSourceListener() {
                override fun onOpen(eventSource: EventSource, response: Response) {
                    sseHandler.onSSEConnectionOpened()
                }

                override fun onEvent(eventSource: EventSource, id: String?, type: String?, data: String) {
                    // SSE 이벤트 수신
                    sseHandler.onSSEEventReceived(type ?: "message", data)
                }

                override fun onClosed(eventSource: EventSource) {
                    sseHandler.onSSEConnectionClosed()
                }

                override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
                    sseHandler.onSSEError(t ?: Exception("Unknown error"))
                }
            })
        } catch (e: Exception) {
            errorCallback(e)
        }
    }

    fun disconnect() {
        eventSource?.cancel()
    }
}
