package com.example.speedotransfer.ui.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.speedotransfer.ui.routes.AppRoutes.LAST_PAGE_SIGN_UP
import com.example.speedotransfer.ui.routes.AppRoutes.SIGN_IN
import com.example.speedotransfer.ui.routes.AppRoutes.SPLASH
import com.example.speedotransfer.ui.routes.AppRoutes.START_DESTINATION
import com.example.speedotransfer.ui.routes.AppRoutes.isFirstTime
import com.example.speedotransfer.ui.screens.auth.onBoarding.OnBoarding1
import com.example.speedotransfer.ui.screens.auth.onBoarding.OnBoarding2
import com.example.speedotransfer.ui.screens.auth.onBoarding.OnBoarding3
import com.example.speedotransfer.ui.screens.auth.signIn.SignIn
import com.example.speedotransfer.ui.screens.auth.signUp.SignUp1
import com.example.speedotransfer.ui.screens.auth.signUp.SignUp2
import com.example.speedotransfer.ui.screens.auth.splash.SplashScreen

@Composable
fun AuthNavGraph(navController: NavController) {
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
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
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
