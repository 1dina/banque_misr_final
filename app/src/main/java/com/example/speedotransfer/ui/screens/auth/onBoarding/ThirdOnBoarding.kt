package com.example.speedotransfer.ui.screens.auth.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.data.source.local.PreferenceManager
import com.example.speedotransfer.ui.routes.AppRoutes
import com.example.speedotransfer.ui.theme.LightRed


@Composable
fun OnBoarding3(onNavigateCallBack: (String) -> Unit) {
    val context = LocalContext.current
    val preferenceManager = PreferenceManager(context)
    preferenceManager.setFirstTimeLaunch(false)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        LightRed
                    )
                )
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Skip",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 20.dp, end = 20.dp)
                    .clickable {
                        onNavigateCallBack(AppRoutes.FIRST_PAGE_SIGN_UP)

                    })
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {


            Image(
                painter = painterResource(id = R.drawable.payments),
                contentDescription = "Confirm",
                modifier = Modifier.size(300.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.onboard3),
                contentDescription = "Second Onboard",
                modifier = Modifier.size(70.dp)
            )

            Text(
                text = "Payment",
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
            )

            Text(
                text = "Enjoy peace of mind with our secure platform Transfer funds instantly to friends.",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 20.dp)
            )

            Button(
                onClick = {
//                    navController.navigate(AppRoutes.FIRST_PAGE_SIGN_UP) {
//                        popUpTo(AppRoutes.THIRD_ONBOARD) { inclusive = true }
//                    }
                    onNavigateCallBack(AppRoutes.FIRST_PAGE_SIGN_UP)
                },
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(width = 343.dp, height = 51.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.Marron))
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp
                )

            }

        }


    }
}

@Preview(showSystemUi = true)
@Composable
private fun OnBoarding3Preview() {
    OnBoarding3 {}
}