package com.example.speedotransfer.ui.screens.dashboard.more.settings

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.routes.AppRoutes
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUI
import com.example.speedotransfer.ui.screens.dashboard.commonUI.ProfileScreen
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightYellow


@Composable
fun Settings(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onNavigateClick: (String) -> Unit
) {

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
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            HeaderUI(headerTitle = "Settings", onClickBackButton = { onBackClick() })

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
                        .clickable { onNavigateClick(AppRoutes.UPDATE_PASSWORD) }
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


                HorizontalDivider(
                    modifier = Modifier
                        .padding(vertical = 0.dp)
                        .padding(start = 15.dp)
                        .width(370.dp)
                        .alpha(0.2f),
                    thickness = 2.dp,
                    color = Color.Gray
                )


            }

        }
    }

}
