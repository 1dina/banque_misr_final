package com.example.speedotransfer.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.routes.AppRoutes
import com.example.speedotransfer.ui.screens.dashboard.commonUI.ProfileScreen
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUI
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightYellow


@Composable
fun Settings(navController: NavController, modifier : Modifier = Modifier) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        LightYellow,
                        LightRed
                    )
                )
            )
    ) {
        Column (modifier = modifier.fillMaxSize().padding(16.dp) ) {
            HeaderUI(headerTitle = "Settings", onClickBackButton = { navController.popBackStack() })

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 64.dp)
                    .fillMaxSize()
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .clickable { navController.navigate(AppRoutes.UPDATE_PASSWORD) }
                ) {
                    ProfileScreen(
                        text1 = "Change password",
                        text2 = "Change password",
                        image = painterResource(
                            id = R.drawable.changepassword
                        ),
                        imageText = "Lock",
                        onItemClick = {}

                    )
                }



                Row(
                    modifier = Modifier
                        .padding(top = 5.dp)
                ) {
                    ProfileScreen(
                        text1 = "Edit Profile",
                        text2 = "Change your information",
                        image = painterResource(
                            id = R.drawable.editprofile
                        ),
                        imageText = "Edit Profile Details",
                        onItemClick = {}

                    )
                }


                Divider(
                    modifier = Modifier
                        .padding(vertical = 0.dp)
                        .padding(start = 15.dp)
                        .width(370.dp)
                        .alpha(0.2f),
                    color = Color.Gray,
                    thickness = 2.dp
                )


            }

        }    }

}
