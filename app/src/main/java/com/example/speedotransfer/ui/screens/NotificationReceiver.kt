package com.example.speedotransfer.ui.screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.speedotransfer.ui.screens.dashboard.transfer.sendNotification

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        sendNotification(
            intent.getStringExtra("title")!!,
            intent.getStringExtra("text")!!,
            context)
    }
}