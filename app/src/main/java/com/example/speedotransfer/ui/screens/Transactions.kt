package com.example.speedotransfer.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.Green
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Marron
import com.example.speedotransfer.ui.theme.Red
import com.example.speedotransfer.ui.theme.TransparentGreen
import com.example.speedotransfer.ui.theme.TransparentRed


@Composable
fun Transactions(modifier : Modifier  = Modifier) {

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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "Back",
                Modifier
                    .size(40.dp)
                    .padding(top = 16.dp)
                    .clickable { }
            )

            Text(
                text = "Transactions",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 15.dp, start = 100.dp)

            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(-20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 70.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Your Last Transactions",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 32.dp),
                fontWeight = FontWeight.Medium
            )

            Card(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .size(380.dp, 120.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
                Row {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = 10.dp, top = 10.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .size(60.dp, 60.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.visaicon),
                                contentDescription = "Visa Image",
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }
                    Column(
                        modifier =
                        Modifier.padding(top = 15.dp, start = 10.dp)
                    ) {
                        Text(
                            text = "User 1",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Visa . Master Card . 1234",
                            fontSize = 12.sp,
                            modifier = Modifier.alpha(0.8f)
                        )
                        Text(
                            text = "Today 11:00 - Received",
                            fontSize = 12.sp,
                            modifier = Modifier.alpha(0.6f)
                        )

                        Text(
                            text = "$1000",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .padding(top = 20.dp),
                            style = TextStyle(color = Marron)
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_forward),
                            contentDescription = "See Details",
                            Modifier
                                .size(40.dp)
                                .padding(top = 16.dp)
                                .alpha(0.5f)
                                .clickable {  }
                        )

                        Box (
                            modifier = Modifier
                                .padding(end = 10.dp, top = 20.dp)
                                .size(100.dp, 25.dp)
                                .background(
                                    color = TransparentGreen,
                                    shape = RoundedCornerShape(8.dp)
                                )

                        ) {

                            Text(
                                text = "Successful",
                                fontSize = 14.sp,
                                modifier = Modifier.align(Alignment.Center),
                                style = TextStyle(color = Green)
                            )

                        }
                    }
                }

            }

            Card(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .size(380.dp, 120.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
                Row {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = 10.dp, top = 10.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .size(60.dp, 60.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.bank),
                                contentDescription = "Bank Image",
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }
                    Column(
                        modifier =
                        Modifier.padding(top = 15.dp, start = 10.dp)
                    ) {
                        Text(
                            text = "User 1",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Visa . Master Card . 1234",
                            fontSize = 12.sp,
                            modifier = Modifier.alpha(0.8f)
                        )
                        Text(
                            text = "Today 11:00 - Received",
                            fontSize = 12.sp,
                            modifier = Modifier.alpha(0.6f)
                        )

                        Text(
                            text = "$1000",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .padding(top = 20.dp),
                            style = TextStyle(color = Marron)
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_forward),
                            contentDescription = "Select your country",
                            Modifier
                                .size(40.dp)
                                .padding(top = 16.dp)
                                .alpha(0.5f)
                                .clickable { }
                        )

                        Box(
                            modifier = Modifier
                                .padding(end = 10.dp, top = 20.dp)
                                .size(60.dp, 25.dp)
                                .background(
                                    color = TransparentRed,
                                    shape = RoundedCornerShape(8.dp)
                                )

                        ) {

                            Text(
                                text = "Failed",
                                fontSize = 14.sp,
                                modifier = Modifier.align(Alignment.Center),
                                style = TextStyle(color = Red)
                            )

                        }
                    }
                }
            }

            Card(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .size(380.dp, 120.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
                Row {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = 10.dp, top = 10.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .size(60.dp, 60.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.visaicon),
                                contentDescription = "Visa Image",
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }
                    Column(
                        modifier =
                        Modifier.padding(top = 15.dp, start = 10.dp)
                    ) {
                        Text(
                            text = "User 1",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Visa . Master Card . 1234",
                            fontSize = 12.sp,
                            modifier = Modifier.alpha(0.8f)
                        )
                        Text(
                            text = "Today 11:00 - Received",
                            fontSize = 12.sp,
                            modifier = Modifier.alpha(0.6f)
                        )

                        Text(
                            text = "$1000",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .padding(top = 20.dp),
                            style = TextStyle(color = Marron)
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_forward),
                            contentDescription = "Select your country",
                            Modifier
                                .size(40.dp)
                                .padding(top = 16.dp)
                                .alpha(0.5f)
                                .clickable { }
                        )

                        Box (
                            modifier = Modifier
                                .padding(end = 10.dp, top = 20.dp)
                                .size(100.dp, 25.dp)
                                .background(
                                    color = TransparentGreen,
                                    shape = RoundedCornerShape(8.dp)
                                )

                        ) {

                            Text(
                                text = "Successful",
                                fontSize = 14.sp,
                                modifier = Modifier.align(Alignment.Center),
                                style = TextStyle(color = Green)
                            )

                        }
                    }
                }

            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun TransactionsPreview() {
    Transactions()
}