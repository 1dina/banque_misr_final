package com.example.speedotransfer.ui.screens.dashboard.components.mycards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.speedotransfer.routes.AppRoutes
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUIWithCancel
import com.example.speedotransfer.ui.theme.LightPink
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Maroon

@Composable
fun CardCurrency(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        LightYellow, LightPink
                    )
                )
            )
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            HeaderUIWithCancel(
                headerTitle = "Select Currency",
                headerOption = "Cancel",
                onClickBackButton = { navController.popBackStack() }, onOptionClick = {navController.popBackStack()},
                modifier
            )
            // here yo make list of countries

            Column(modifier = modifier.fillMaxSize()) {
                Text(text = "Countries", modifier = modifier.weight(1f))

                Button(
                    onClick = { navController.navigate(AppRoutes.CARD_ADDITION)},
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 128.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Maroon,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Get Started",
                        fontSize = 16.sp,
                        modifier = modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

