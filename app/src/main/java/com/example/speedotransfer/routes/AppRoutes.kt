package com.example.speedotransfer.routes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.speedotransfer.routes.AppRoutes.HOME
import com.example.speedotransfer.routes.AppRoutes.LAST_PAGE_SIGN_UP
import com.example.speedotransfer.routes.AppRoutes.SIGN_IN
import com.example.speedotransfer.routes.AppRoutes.SPLASH
import com.example.speedotransfer.routes.AppRoutes.START_DESTINATION
import com.example.speedotransfer.routes.AppRoutes.TRANS_DETAILS
import com.example.speedotransfer.routes.AppRoutes.isFirstTime
import com.example.speedotransfer.ui.screens.SignIn
import com.example.speedotransfer.ui.screens.SignUp1
import com.example.speedotransfer.ui.screens.SignUp2
import com.example.speedotransfer.ui.screens.SplashScreen
import com.example.speedotransfer.ui.screens.TransactionScreen
import com.example.speedotransfer.ui.screens.TransactionsDetails
import com.example.speedotransfer.ui.screens.dashboard.components.HomeScreen
import com.example.speedotransfer.ui.screens.dashboard.components.NotificationScreen
import com.example.speedotransfer.ui.screens.dashboard.components.more.FavouriteScreen
import com.example.speedotransfer.ui.screens.dashboard.components.more.MoreScreen
import com.example.speedotransfer.ui.screens.dashboard.components.mycards.CardAddition
import com.example.speedotransfer.ui.screens.dashboard.components.mycards.MyCardScreen
import com.example.speedotransfer.ui.screens.dashboard.components.transfer.TransferScreen
import com.example.speedotransfer.ui.viewmodels.AuthViewModel
import com.example.speedotransfer.ui.viewmodels.HomeViewModel

object AppRoutes {
    const val SPLASH = "splash"
    const val FIRST_PAGE_SIGN_UP = "signup1"
    const val LAST_PAGE_SIGN_UP = "signup2"
    const val SIGN_IN = "login"
    const val HOME = "home"
    const val TRANSFER = "transfer"
    const val TRANSACTION = "transaction"
    const val MY_CARD = "MyCard"
    const val MORE = "more"
    const val FAVOURITES = "favourites"
    const val NOTIFICATIONS = "notification"
    const val CURRENCY = "cardCurrency"
    const val CARD_ADDITION = "cardAddition"
    const val TRANS_DETAILS = "transactionDetails"
    var isFirstTime = true
    var START_DESTINATION = SPLASH


}


@Composable
fun AuthNavGraph(navController: NavController, authViewModel: AuthViewModel) {
    if (!isFirstTime) START_DESTINATION = SIGN_IN
    else START_DESTINATION = SPLASH
    NavHost(
        navController = navController as NavHostController,
        startDestination = START_DESTINATION
    ) {
        composable(SPLASH) { SplashScreen(navController = navController, onTimeout = {}) }
        composable(SIGN_IN) {
            SignIn(navController = navController)
            isFirstTime = true
        }
        composable(route = "$LAST_PAGE_SIGN_UP/{name}/{email}/{password}",
            arguments = listOf(navArgument("name") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType }
            )) {
            val name = it.arguments?.getString("name")!!
            val email = it.arguments?.getString("email")!!
            val password = it.arguments?.getString("password")!!
            SignUp2(navController = navController, name = name, email = email, password = password)

        }
        composable(AppRoutes.FIRST_PAGE_SIGN_UP) { SignUp1(navController = navController) }
    }
}

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
                innerPadding = innerPadding
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
                innerPadding = innerPadding
            )
        }
        composable(AppRoutes.CARD_ADDITION) { CardAddition(navController = navController) }
        composable(route = "$TRANS_DETAILS/{name}/{amount}/{date}/{accountNum}",
            arguments = listOf(navArgument("name") { type = NavType.StringType },
                navArgument("amount") { type = NavType.IntType },
                navArgument("date") { type = NavType.StringType },
                navArgument("accountNum"){type = NavType.IntType}
            )) {
            val name = it.arguments?.getString("name")!!
            val amount = it.arguments?.getInt("amount")!!
            val date = it.arguments?.getString("date")!!
            val accountNum = it.arguments?.getInt("accountNum")!!
            TransactionsDetails(
                navController = navController,
                innerPaddingValues = innerPadding,
                name = name,
                amount = amount,
                date = date,
                accountNum = accountNum ,
                viewModel = viewModel
            )

        }


    }
}

