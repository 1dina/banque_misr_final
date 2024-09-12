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
import com.example.speedotransfer.ui.screens.auth.OnBoarding1
import com.example.speedotransfer.ui.screens.auth.OnBoarding2
import com.example.speedotransfer.ui.screens.auth.OnBoarding3
import com.example.speedotransfer.ui.screens.dashboard.ChangePassword
import com.example.speedotransfer.ui.screens.dashboard.Profile
import com.example.speedotransfer.ui.screens.dashboard.ProfileInfo
import com.example.speedotransfer.ui.screens.dashboard.Settings
import com.example.speedotransfer.ui.screens.auth.SignIn
import com.example.speedotransfer.ui.screens.auth.SignUp1
import com.example.speedotransfer.ui.screens.auth.SignUp2
import com.example.speedotransfer.ui.screens.auth.SplashScreen
import com.example.speedotransfer.ui.screens.dashboard.TransactionScreen
import com.example.speedotransfer.ui.screens.dashboard.TransactionsDetails
import com.example.speedotransfer.ui.screens.dashboard.components.HomeScreen
import com.example.speedotransfer.ui.screens.dashboard.components.NotificationScreen
import com.example.speedotransfer.ui.screens.dashboard.components.more.FavouriteScreen
import com.example.speedotransfer.ui.screens.dashboard.components.more.MoreScreen
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
    const val TRANS_DETAILS = "transactionDetails"
    var isFirstTime = true
    var START_DESTINATION = SPLASH
    const val PROFILE = "profile"
    const val PROFILE_INFO = "profileInfo"
    const val UPDATE_PASSWORD = "updatepassword"
    const val SETTINGS = "settings"
    const val FIRST_ONBOARD = "first_onboard"
    const val SECOND_ONBOARD = "second_onboard"
    const val THIRD_ONBOARD = "third_onboard"



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
        composable(AppRoutes.FIRST_ONBOARD) { OnBoarding1(navController = navController)  }
        composable(AppRoutes.SECOND_ONBOARD) { OnBoarding2(navController = navController)  }
        composable(AppRoutes.THIRD_ONBOARD) { OnBoarding3(navController = navController)  }

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
            arguments = listOf(navArgument("clientName") { type = NavType.StringType },
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

