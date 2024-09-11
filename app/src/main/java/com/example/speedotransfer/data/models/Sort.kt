package com.example.speedotransfer.data.models

data class Sort(
    val ascending: Boolean,
    val direction: String,
    val ignoreCase: Boolean,
    val nullHandling: String,
    val `property`: String
)