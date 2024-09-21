package com.example.speedotransfer.data.models.historyResponse

data class Sort(
    val ascending: Boolean,
    val direction: String,
    val ignoreCase: Boolean,
    val nullHandling: String,
    val `property`: String
)