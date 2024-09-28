package com.example.speedotransfer.ui.screens.dashboard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.routes.AppRoutes

sealed class BottomNavBarScreens(val route: String, @DrawableRes val itemImage: Int, @StringRes val resourceId: Int) {
    object HomeScreens : BottomNavBarScreens(AppRoutes.HOME,R.drawable.ic_home, R.string.home)
    object TransferScreens : BottomNavBarScreens(AppRoutes.TRANSFER,R.drawable.ic_transfer, R.string.transfer)
    object TransactionScreens : BottomNavBarScreens(AppRoutes.TRANSACTION,R.drawable.ic_transaction, R.string.transactions)
    object MyCardScreens : BottomNavBarScreens(AppRoutes.MY_CARD,R.drawable.ic_cards, R.string.my_cards)
    object MoreScreens : BottomNavBarScreens(AppRoutes.MORE,R.drawable.ic_more, R.string.more)

    companion object {
        fun getBottomNavBarItems(): List<BottomNavBarScreens> {
            val items = listOf(
                HomeScreens, TransferScreens, TransactionScreens, MyCardScreens, MoreScreens
            )
            return items
        }
    }
}