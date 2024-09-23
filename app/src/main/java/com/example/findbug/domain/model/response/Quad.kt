package com.example.findbug.domain.model.response

data class Quad<A, B, C, D>(
    val first: A?,
    val second: B?,
    val third: C?,
    val fourth: D?
)