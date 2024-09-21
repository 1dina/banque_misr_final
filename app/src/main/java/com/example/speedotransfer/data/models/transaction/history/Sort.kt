package com.example.speedotransfer.data.models.transaction.history

data class Sort(
    val ascending: Boolean,
    val direction: String,
    val ignoreCase: Boolean,
    val nullHandling: String,
    val `property`: String
)