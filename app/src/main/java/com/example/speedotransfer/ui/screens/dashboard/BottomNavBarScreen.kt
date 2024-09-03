package com.example.speedotransfer.ui.screens.dashboard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.speedotransfer.R
import com.example.speedotransfer.routes.DashboardRoute

sealed class BottomNavBarScreen(val route: String,@DrawableRes val itemImage: Int, @StringRes val resourceId: Int) {
    object HomeScreen : BottomNavBarScreen(DashboardRoute.HOME,R.drawable.ic_home, R.string.home)
    object TransferScreen : BottomNavBarScreen(DashboardRoute.TRANSFER,R.drawable.ic_transfer, R.string.transfer)
    object TransactionScreen : BottomNavBarScreen(DashboardRoute.TRANSACTION,R.drawable.ic_transaction, R.string.transactions)
    object MyCardScreen : BottomNavBarScreen(DashboardRoute.MY_CARD,R.drawable.ic_cards, R.string.my_cards)
    object MoreScreen : BottomNavBarScreen(DashboardRoute.MORE,R.drawable.ic_more, R.string.more)

    companion object {
        fun getBottomNavBarItems(): List<BottomNavBarScreen> {
            val items = listOf(
                HomeScreen, TransferScreen, TransactionScreen, MyCardScreen, MoreScreen
            )
            return items
        }
    }
}