package com.example.findbug.base

data class BaseResponse<T> (
    val isSuccess: Boolean = false,
    val code: String = "",
    val message: String = "",
    val result: T? = null
)

data class BaseListResponse<T> (
    val isSuccess: Boolean = false,
    val code: String = "",
    val message: String = "",
    val result: List<T>? = null
)
