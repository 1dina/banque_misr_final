package com.example.speedotransfer.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.speedotransfer.R
import com.example.speedotransfer.data.source.local.SecureStorageDataSource
import com.example.speedotransfer.routes.AppRoutes
import com.example.speedotransfer.ui.theme.DarkRed
import com.example.speedotransfer.data.source.local.PreferenceManager
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    onTimeout: () -> Unit
) {
    val context = LocalContext.current
    val preferenceManager = PreferenceManager(context)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkRed),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Speedo Transfer",
            fontSize = 32.sp,
            color = colorResource(id = R.color.white)
        )
    }

    LaunchedEffect(Unit) {
        delay(3000)
        onTimeout()
            if (!(preferenceManager.isFirstTimeLaunch()))
                navController.navigate(AppRoutes.FIRST_PAGE_SIGN_UP) {
                    popUpTo(AppRoutes.SPLASH) { inclusive = true }
                }
            else navController.navigate(AppRoutes.FIRST_ONBOARD) {
                popUpTo(AppRoutes.SPLASH) { inclusive = true }
            }


    }
}

