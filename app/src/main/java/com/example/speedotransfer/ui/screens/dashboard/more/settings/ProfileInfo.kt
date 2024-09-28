package com.example.speedotransfer.ui.screens.dashboard.more.settings


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUI
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.viewmodels.home.HomeViewModel


@Composable
fun ProfileInfo(
    navController: NavController, modifier: Modifier = Modifier, viewModel: HomeViewModel
) {
    viewModel.getUserInfo()
    val userInfo by viewModel.userInfoData.collectAsState()

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
        Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
            HeaderUI(
                headerTitle = "Profile info",
                onClickBackButton = { navController.popBackStack() })
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 120.dp, start = 8.dp,end = 8.dp)
                    .fillMaxSize()
            ) {

                Text(
                    text = "Full Name",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.Start)
                )

                userInfo?.let {
                    Text(
                        text = it.name,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .alpha(0.5f)
                    )
                }

                Divider(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .padding(start = 15.dp)
                        .width(370.dp)
                        .alpha(0.2f),
                    color = Color.Gray,
                    thickness = 2.dp
                )

                Text(
                    text = "Email",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.Start)
                )

                userInfo?.email?.let {
                    Text(
                        text = it,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .alpha(0.5f)
                    )
                }

                Divider(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .padding(start = 15.dp)
                        .width(370.dp)
                        .alpha(0.2f),
                    color = Color.Gray,
                    thickness = 2.dp
                )

                Text(
                    text = "Date Of Birth",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.Start)
                )

                userInfo?.dateofbirth?.let {
                    Text(
                        text = it,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .alpha(0.5f)
                    )
                }

                Divider(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .padding(start = 15.dp)
                        .width(370.dp)
                        .alpha(0.2f),
                    color = Color.Gray,
                    thickness = 2.dp
                )

                Text(
                    text = "Country",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.Start)
                )

                userInfo?.country?.let {
                    Text(
                        text = it,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .alpha(0.5f)
                    )
                }

                Divider(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .padding(start = 15.dp)
                        .width(370.dp)
                        .alpha(0.2f),
                    color = Color.Gray,
                    thickness = 2.dp
                )

                Text(
                    text = "Bank Account",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.Start)
                )


                var accountNumber = userInfo?.accounts?.get(0)?.id.toString()
                val accountNumberMod = "xxxx" + accountNumber.takeLast(4)

                Text(
                    text = accountNumberMod,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .alpha(0.5f)
                )

                Divider(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .padding(start = 15.dp)
                        .width(370.dp)
                        .alpha(0.2f),
                    color = Color.Gray,
                    thickness = 2.dp
                )


            }
        }
    }
}

@Preview
@Composable
private fun ProfileInfoPreview() {

    //ProfileInfo()

}