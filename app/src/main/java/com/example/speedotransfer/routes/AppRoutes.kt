package com.example.speedotransfer.routes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.speedotransfer.ui.screens.dashboard.components.HomeScreen
import com.example.speedotransfer.ui.screens.dashboard.components.more.MoreScreen
import com.example.speedotransfer.ui.screens.dashboard.components.MyCardScreen
import com.example.speedotransfer.ui.screens.dashboard.components.TransactionScreen
import com.example.speedotransfer.ui.screens.dashboard.components.more.FavouriteScreen
import com.example.speedotransfer.ui.screens.dashboard.components.transfer.TransferScreen

object AppRoutes {
    const val HOME = "home"
    const val TRANSFER = "transfer"
    const val TRANSACTION = "transaction"
    const val MY_CARD = "MyCard"
    const val MORE = "more"
    const val FAVOURITES = "favourites"
}

@Composable
fun AppNavHost(navController: NavController, innerPadding: PaddingValues) {
    NavHost(navController = navController as NavHostController, startDestination = AppRoutes.HOME) {
        composable(route = AppRoutes.HOME) { HomeScreen(navController, innerPadding = innerPadding) }
        composable(route = AppRoutes.TRANSFER) { TransferScreen(navController,innerPadding = innerPadding) }
        composable(route = AppRoutes.TRANSACTION) { TransactionScreen(navController,innerPadding = innerPadding) }
        composable(route = AppRoutes.MY_CARD) { MyCardScreen(navController,innerPadding = innerPadding) }
        composable(route = AppRoutes.MORE) { MoreScreen(navController,innerPadding = innerPadding) }
        composable(route = AppRoutes.FAVOURITES) { FavouriteScreen(navController,innerPadding = innerPadding) }


    }
}