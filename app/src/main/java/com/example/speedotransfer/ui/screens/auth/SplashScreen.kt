package com.example.speedotransfer.ui.screens.auth

import android.content.Intent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.ui.screens.dashboard.DashboardActivity
import com.example.speedotransfer.R
import com.example.speedotransfer.data.source.local.SecureStorageDataSource
import com.example.speedotransfer.routes.AppRoutes
import com.example.speedotransfer.ui.theme.DarkRed
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController,modifier : Modifier = Modifier, onTimeout: () -> Unit) {
    val context = LocalContext.current
    val encryptedSharedPreference = SecureStorageDataSource(context)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkRed),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Speedo Transfer",
            fontSize = 32.sp,
            color = colorResource(id = R.color.white)
        )
    }

    // Adding a delay before navigating to the main screen
    LaunchedEffect(Unit) {
        delay(3000) // 2000 milliseconds = 2 seconds
        onTimeout()
     if(encryptedSharedPreference.getAccessToken().isNullOrEmpty()) {
         navController.navigate(AppRoutes.FIRST_PAGE_SIGN_UP) {
             popUpTo(AppRoutes.SPLASH) { inclusive = true }
         }
     }else {
         val intent = Intent(context, DashboardActivity::class.java).apply {
             flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
         }
         context.startActivity(intent)
     }

    }
}

@Preview
@Composable
private fun SplashScreenPreview() {

    SplashScreen(rememberNavController()) {

    }

}