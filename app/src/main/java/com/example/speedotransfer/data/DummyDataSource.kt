package com.example.speedotransfer.data

import com.example.speedotransfer.R


object DummyDataSource {
    fun getDataSource(): List<RecentTransactionItem> {
        val transactionItem = RecentTransactionItem(
            R.drawable.ic_visa,
            "Ahmed Mohamed",
            "Visa. Mater Card . 1234",
            "Today 11:20",
            "received",
            "500EGP"
        )
        val list = mutableListOf<RecentTransactionItem>()
        for (i in 0..9) {
            list.add(transactionItem)
        }
        return list
    }

}