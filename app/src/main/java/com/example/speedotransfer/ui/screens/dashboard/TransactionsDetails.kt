package com.example.speedotransfer.ui.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.screens.dashboard.commonUI.TransferProcessCard
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUI
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightWhite
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Marron
import com.example.speedotransfer.ui.viewmodels.HomeViewModel

@Composable
fun TransactionsDetails(
    navController: NavController, innerPaddingValues: PaddingValues,
    name: String, amount: Int, date: String,
    accountNum : Int,
    viewModel: HomeViewModel, modifier: Modifier = Modifier
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
            .padding(innerPaddingValues)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            HeaderUI(headerTitle = "Successful Transactions", onClickBackButton = {
                navController.popBackStack()

            })

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxSize()
            ) {

                Image(
                    painter = painterResource(id = R.drawable.completed),
                    contentDescription = "Transaction Completed",
                    modifier = Modifier
                        .size(150.dp)
                )

                Row() {
                    Text(
                        text = amount.toString(),
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(end = 2.dp, top = 3.dp)
                    )

                    Text(
                        text = "EGP",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 1.dp, top = 3.dp),
                        style = TextStyle(color = Marron)
                    )
                }

                Text(
                    text = "Transfer amount",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(start = 1.dp, top = 2.dp)
                        .alpha(0.6f)
                )

                Text(
                    text = "Send money",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = 1.dp, top = 3.dp),
                    style = TextStyle(color = Marron)
                )


                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Column {
                        TransferProcessCard(
                            destination = "From",
                            cardUser = "Asmaa Dosuky",
                            cardAccount = "xxxx7890",
                        )
                        TransferProcessCard(
                            destination = "To",
                            cardUser = name,
                            cardAccount = accountNum.toString(),

                            )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.completed),
                        contentDescription = "completed",
                        modifier = modifier
                            .align(Alignment.Center)

                    )
                }
                Card(
                    modifier = modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    colors = CardDefaults.cardColors(containerColor = LightWhite)
                ) {
                    Column(
                        modifier = modifier
                            .padding(vertical = 24.dp)
                            .wrapContentHeight()
                    ) {
                        Row(
                            modifier = modifier
                                .padding(start = 15.dp, end = 15.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Transfer amount",
                                fontSize = 15.sp,
                                modifier = Modifier.alpha(0.7f)
                            )

                            Row {
                                Text(
                                    text = amount.toString(),
                                    fontSize = 15.sp,
                                    modifier = Modifier
                                        .alpha(0.4f)
                                )

                                Text(
                                    text = "EGP",
                                    fontSize = 15.sp,
                                    modifier = Modifier
                                        .alpha(0.4f)
                                        .padding(start = 5.dp)
                                )
                            }


                        }
                        Divider(
                            modifier = modifier
                                .padding(vertical = 16.dp)
                                .fillMaxWidth(0.9f)
                                .align(Alignment.CenterHorizontally)
                                .alpha(0.4f),
                            color = Color.Gray,
                            thickness = 1.dp
                        )

                        Row(
                            modifier = modifier
                                .padding(horizontal = 15.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Reference",
                                fontSize = 15.sp,
                                modifier = modifier.alpha(0.7f)
                            )

                            Text(
                                text = "123456789101",
                                fontSize = 15.sp,
                                modifier = modifier
                                    .alpha(0.4f)
                            )
                        }
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 15.dp, end = 15.dp,
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Date",
                                fontSize = 15.sp,
                                modifier = modifier.alpha(0.7f)
                            )

                            Text(
                                text = date,
                                fontSize = 15.sp,
                                modifier = modifier
                                    .alpha(0.4f)


                            )

                        }
                    }

                }
            }
        }

    }

}


