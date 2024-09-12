package com.example.speedotransfer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.routes.AppRoutes
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUI
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightWhite
import com.example.speedotransfer.ui.theme.LightYellow


@Composable
fun Profile(navController: NavController, modifier: Modifier = Modifier) {

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
            modifier = Modifier.fillMaxSize()
                .padding( 16.dp)
        ) {
            HeaderUI(headerTitle = "Profile", onClickBackButton = {
                navController.popBackStack()
            })
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 30.dp, start = 15.dp)
                        .size(60.dp)
                        .background(LightWhite, shape = CircleShape),
                ) {
                    Text(
                        text = "AD",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .alpha(0.7f),
                        style = TextStyle(color = Color.Gray)
                    )
                }
                Text(
                    text = "Asmaa Dosuky",
                    fontWeight = FontWeight.Bold,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(top = 42.dp, start = 15.dp)

                )

            }



            Column(modifier = modifier.padding(top = 24.dp).fillMaxSize()) {

                Row(
                    modifier = Modifier
                        .fillMaxHeight(0.1f)
                        .padding(top = 5.dp)
                        .clickable { navController.navigate(AppRoutes.PROFILE_INFO) }
                ) {
                    ProfileScreen(
                        text1 = "Profile information",
                        text2 = "Your information",
                        image = painterResource(
                            id = R.drawable.personalinfo
                        ),
                        imageText = "Settings",
                        onItemClick = {

                        }
                    )

                }

                Divider(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .padding(start = 15.dp)
                        .width(370.dp)
                        .alpha(0.2f),
                    color = Color.Gray,
                    thickness = 2.dp
                )

                Row(
                    modifier = Modifier
                        .fillMaxHeight(0.13f)
                        .padding(top = 5.dp)
                        .clickable { navController.navigate(AppRoutes.SETTINGS) }
                ) {
                    ProfileScreen(
                        text1 = "Setting", text2 = "Change your settings", image = painterResource(
                            id = R.drawable.settings
                        ), imageText = "Settings",
                        onItemClick = {}

                    )
                }


                Divider(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .padding(start = 15.dp)
                        .width(370.dp)
                        .alpha(0.2f),
                    color = Color.Gray,
                    thickness = 2.dp
                )



                Row(
                    modifier = Modifier
                        .fillMaxHeight(0.13f)
                        .padding(top = 5.dp)
                ) {
                    ProfileScreen(
                        text1 = "Payment history",
                        text2 = "view your transactions",
                        image = painterResource(
                            id = R.drawable.paymenthistory
                        ),
                        imageText = "payment",
                        onItemClick = {}

                    )
                }


                Divider(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .padding(start = 15.dp)
                        .width(370.dp)
                        .alpha(0.2f),
                    color = Color.Gray,
                    thickness = 2.dp
                )


                Row(
                    modifier = Modifier
                        .fillMaxHeight(0.20f)
                        .padding(top = 5.dp)
                ) {

                    ProfileScreen(
                        text1 = "My Favourite list",
                        text2 = "view your favourites",
                        image = painterResource(
                            id = R.drawable.favourites
                        ),
                        imageText = "favourite",
                        onItemClick = {}

                    )
                }

            }

        }
    }
}


@Preview
@Composable
private fun ProfilePreview() {
    Profile(rememberNavController())
}
