package com.example.speedotransfer.data

import com.example.speedotransfer.R
import com.example.speedotransfer.data.models.FavoriteListItem
import com.example.speedotransfer.data.models.RecentTransactionItem


object DummyDataSource {
    fun getRecentTransactionsData(): List<RecentTransactionItem> {
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
    fun getFavoriteRecipentData() : List<FavoriteListItem>{
        val favoriteUser = FavoriteListItem(
            "Asmaa Dosuky",
            "xxxx7890"
        )
        val list = mutableListOf<FavoriteListItem>()
        for (i in 0..9) {
            list.add(favoriteUser)
        }
        return list
    }

}