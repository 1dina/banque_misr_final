package com.example.speedotransfer.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.speedotransfer.ui.screens.dashboard.HomeScreen
import com.example.speedotransfer.ui.screens.dashboard.MoreScreen
import com.example.speedotransfer.ui.screens.dashboard.MyCardScreen
import com.example.speedotransfer.ui.screens.dashboard.TransactionScreen
import com.example.speedotransfer.ui.screens.dashboard.TransferScreen

object DashboardRoute {
    const val HOME = "home"
    const val TRANSFER = "transfer"
    const val TRANSACTION = "transaction"
    const val MY_CARD = "MyCard"
    const val MORE = "more"
}

@Composable
fun BottomNavGraph(navController: NavController, modifier: Modifier = Modifier) {
    NavHost(navController = navController as NavHostController, startDestination = DashboardRoute.HOME) {
        composable(route = DashboardRoute.HOME) { HomeScreen(navController) }
        composable(route = DashboardRoute.TRANSFER) { TransferScreen(navController) }
        composable(route = DashboardRoute.TRANSACTION) { TransactionScreen(navController) }
        composable(route = DashboardRoute.MY_CARD) { MyCardScreen(navController) }
        composable(route = DashboardRoute.MORE) { MoreScreen(navController) }

    }
}