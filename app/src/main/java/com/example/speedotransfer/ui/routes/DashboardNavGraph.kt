package com.example.speedotransfer.ui.routes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.speedotransfer.ui.routes.AppRoutes.HOME
import com.example.speedotransfer.ui.routes.AppRoutes.TRANS_DETAILS
import com.example.speedotransfer.ui.screens.dashboard.home.HomeScreen
import com.example.speedotransfer.ui.screens.dashboard.home.NotificationScreen
import com.example.speedotransfer.ui.screens.dashboard.more.FavouriteScreen
import com.example.speedotransfer.ui.screens.dashboard.more.MoreScreen
import com.example.speedotransfer.ui.screens.dashboard.more.settings.ChangePassword
import com.example.speedotransfer.ui.screens.dashboard.more.settings.Profile
import com.example.speedotransfer.ui.screens.dashboard.more.settings.ProfileInfo
import com.example.speedotransfer.ui.screens.dashboard.more.settings.Settings
import com.example.speedotransfer.ui.screens.dashboard.mycards.MyCardScreen
import com.example.speedotransfer.ui.screens.dashboard.transaction.TransactionScreen
import com.example.speedotransfer.ui.screens.dashboard.transaction.TransactionsDetails
import com.example.speedotransfer.ui.screens.dashboard.transfer.TransferScreen
import com.example.speedotransfer.ui.viewmodels.home.HomeViewModel

@Composable
fun DashboardNavGraph(
    navController: NavController, innerPadding: PaddingValues, viewModel: HomeViewModel
) {
    NavHost(navController = navController as NavHostController, startDestination = HOME) {
        composable(AppRoutes.HOME) {
            HomeScreen(
                innerPadding = innerPadding
            ) { route ->
                navController.navigate(route)
            }
        }
        composable(AppRoutes.TRANSFER) {
            TransferScreen(
                innerPadding = innerPadding
            ) { route ->
                navController.navigate(route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                }
            }
        }
        composable(AppRoutes.TRANSACTION) {
            TransactionScreen(
                innerPadding = innerPadding, onBackClick = { navController.popBackStack() },
                onNavigationCallBack = { receiverName, amount, createdTimeStamp,
                                         reciverAccountId, senderName, state, senderAccountId ->
                    navController.navigate(
                        "${TRANS_DETAILS}/${receiverName}/${amount}/${createdTimeStamp}" +
                                "/${reciverAccountId}/${senderName}/${state}/${senderAccountId}"
                    )

                })

        }
        composable(AppRoutes.MY_CARD) {
            MyCardScreen(
                innerPadding = innerPadding,
            ) {
                navController.popBackStack()
            }
        }
        composable(AppRoutes.MORE) {
            MoreScreen(
                innerPadding = innerPadding
            ) { route ->
                if (route == AppRoutes.HOME) {
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                    }
                } else
                    navController.navigate(route)
            }
        }
        composable(AppRoutes.FAVOURITES) {
            FavouriteScreen(
                innerPadding = innerPadding,
            ) {
                navController.popBackStack()
            }
        }
        composable(AppRoutes.NOTIFICATIONS) {
            NotificationScreen(
                innerPadding = innerPadding,
            ) {
                navController.popBackStack()
            }
        }
        composable(route = "$TRANS_DETAILS/{clientName}/{amount}/{date}/{accountNum}/{currentUserName}/{state}/{currentUserAccNum}",
            arguments = listOf(
                navArgument("clientName") { type = NavType.StringType },
                navArgument("amount") { type = NavType.IntType },
                navArgument("date") { type = NavType.StringType },
                navArgument("accountNum") { type = NavType.IntType },
                navArgument("currentUserName") { type = NavType.StringType },
                navArgument("state") { type = NavType.StringType },
                navArgument("currentUserAccNum") { type = NavType.IntType }
            )) {
            val name = it.arguments?.getString("clientName")!!
            val amount = it.arguments?.getInt("amount")!!
            val date = it.arguments?.getString("date")!!
            val accountNum = it.arguments?.getInt("accountNum")!!
            val currentUserName = it.arguments?.getString("currentUserName")!!
            val state = it.arguments?.getString("state")!!
            val currentAcc = it.arguments?.getInt("currentUserAccNum")!!
            TransactionsDetails(
                innerPaddingValues = innerPadding,
                strangeName = name,
                amount = amount,
                date = date,
                strangeAccNum = accountNum,
                currentUserName = currentUserName,
                state = state,
                currentUserAccountNum = currentAcc

            ) {
                navController.popBackStack()
            }

        }
        composable(AppRoutes.PROFILE) {
            Profile(viewModel = viewModel, onNavigationCallBack = { route ->
                navController.navigate(route)
            }, onBackClick = {
                navController.popBackStack()
            })
        }
        composable(AppRoutes.PROFILE_INFO) { ProfileInfo(viewModel = viewModel) { navController.popBackStack() } }
        composable(AppRoutes.UPDATE_PASSWORD) { ChangePassword { navController.popBackStack() } }
        composable(AppRoutes.SETTINGS) {
            Settings(onBackClick = { navController.popBackStack() }) { route ->
                navController.navigate(route)
            }
        }


    }
}

