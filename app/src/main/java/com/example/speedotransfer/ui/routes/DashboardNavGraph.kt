package com.example.speedotransfer.ui.routes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.speedotransfer.ui.routes.AppRoutes.HOME
import com.example.speedotransfer.ui.routes.AppRoutes.TRANS_DETAILS
import com.example.speedotransfer.ui.screens.dashboard.ChangePassword
import com.example.speedotransfer.ui.screens.dashboard.Profile
import com.example.speedotransfer.ui.screens.dashboard.ProfileInfo
import com.example.speedotransfer.ui.screens.dashboard.Settings
import com.example.speedotransfer.ui.screens.dashboard.TransactionScreen
import com.example.speedotransfer.ui.screens.dashboard.TransactionsDetails
import com.example.speedotransfer.ui.screens.dashboard.components.HomeScreen
import com.example.speedotransfer.ui.screens.dashboard.components.NotificationScreen
import com.example.speedotransfer.ui.screens.dashboard.components.more.FavouriteScreen
import com.example.speedotransfer.ui.screens.dashboard.components.more.MoreScreen
import com.example.speedotransfer.ui.screens.dashboard.components.mycards.MyCardScreen
import com.example.speedotransfer.ui.screens.dashboard.components.transfer.TransferScreen
import com.example.speedotransfer.ui.viewmodels.HomeViewModel

@Composable
fun DashboardNavGraph(
    navController: NavController, innerPadding: PaddingValues, viewModel: HomeViewModel
) {
    NavHost(navController = navController as NavHostController, startDestination = HOME) {
        composable(AppRoutes.HOME) {
            HomeScreen(
                navController = navController,
                innerPadding = innerPadding,
                viewModel = viewModel
            )
        }
        composable(AppRoutes.TRANSFER) {
            TransferScreen(
                navController = navController,
                innerPadding = innerPadding,
                viewModel = viewModel
            )
        }
        composable(AppRoutes.TRANSACTION) {
            TransactionScreen(
                navController = navController,
                innerPadding = innerPadding,
                viewModel = viewModel
            )
        }
        composable(AppRoutes.MY_CARD) {
            MyCardScreen(
                navController = navController,
                innerPadding = innerPadding,
                viewModel
            )
        }
        composable(AppRoutes.MORE) {
            MoreScreen(
                navController = navController,
                innerPadding = innerPadding
            )
        }
        composable(AppRoutes.FAVOURITES) {
            FavouriteScreen(
                navController = navController,
                innerPadding = innerPadding,
            )
        }
        composable(AppRoutes.NOTIFICATIONS) {
            NotificationScreen(
                navController = navController,
                innerPadding = innerPadding,
                viewModel = viewModel
            )
        }
        composable(route = "$TRANS_DETAILS/{clientName}/{amount}/{date}/{accountNum}/{currentUserName}/{state}/{currentUserAccNum}",
            arguments = listOf(
                navArgument("clientName") { type = NavType.StringType },
                navArgument("amount") { type = NavType.IntType },
                navArgument("date") { type = NavType.StringType },
                navArgument("accountNum") { type = NavType.IntType },
                navArgument("currentUserName") { type = NavType.StringType},
                navArgument("state") {type = NavType.StringType},
                navArgument("currentUserAccNum"){type = NavType.IntType}
            )) {
            val name = it.arguments?.getString("clientName")!!
            val amount = it.arguments?.getInt("amount")!!
            val date = it.arguments?.getString("date")!!
            val accountNum = it.arguments?.getInt("accountNum")!!
            val currentUserName = it.arguments?.getString("currentUserName")!!
            val state  = it.arguments?.getString("state")!!
            val currentAcc = it.arguments?.getInt("currentUserAccNum")!!
            TransactionsDetails(
                navController = navController,
                innerPaddingValues = innerPadding,
                strangeName = name,
                amount = amount,
                date = date,
                strangeAccNum = accountNum,
                currentUserName = currentUserName,
                state = state,
                currentUserAccountNum = currentAcc

            )

        }
        composable(AppRoutes.PROFILE) { Profile(navController = navController,viewModel = viewModel) }
        composable(AppRoutes.PROFILE_INFO) { ProfileInfo(navController = navController,viewModel = viewModel) }
        composable(AppRoutes.UPDATE_PASSWORD) { ChangePassword(navController)  }
        composable(AppRoutes.SETTINGS) { Settings(navController = navController) }



    }
}

