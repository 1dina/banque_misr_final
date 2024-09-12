package com.example.speedotransfer.data

import com.example.speedotransfer.data.models.dummy.NotificationItemData


object DummyDataSource {


    fun getNotificationData(): List<NotificationItemData> {
        val notificationItem = NotificationItemData(
            "Receive Transaction",
            "You have received 1000 USD from Asmaa Dosuky 1234 xxx", "12 Jul 2024 09:00 PM"
        )
        val list = mutableListOf<NotificationItemData>()
        for (i in 0..4) {
            list.add(notificationItem)
        }
        return list
    }


}