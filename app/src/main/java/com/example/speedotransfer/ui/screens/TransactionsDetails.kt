package com.example.speedotransfer.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightWhite
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Marron

@Composable
fun TransactionsDetails( modifier: Modifier = Modifier) {

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
                text = "Successful Transactions",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 15.dp, start = 60.dp)

            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 90.dp)
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
                    text = "1000",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = 2.dp,top =3.dp)
                )

                Text(
                    text = "USD",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 1.dp , top =3.dp),
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
                    .padding(start = 1.dp , top =3.dp),
                style = TextStyle(color = Marron)
            )


            Box(
                modifier = modifier
                    .fillMaxWidth(0.95f)
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
                        cardUser = "Jonathon Smith",
                        cardAccount = "xxxx7890",

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
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight(0.7f),
                colors = CardDefaults.cardColors(containerColor = LightWhite)
            ){
                Row (
                    modifier = Modifier.padding(top = 25.dp, start = 15.dp)
                ){
                    Text(text = "Transfer amount amount",
                        fontSize = 15.sp,
                        modifier = Modifier.alpha(0.7f))

                    Text(text = "48,4220",
                        fontSize = 15.sp,
                        modifier = Modifier
                            .alpha(0.4f)
                            .padding(start = 100.dp))

                    Text(text = "EGP",
                        fontSize = 15.sp,
                        modifier = Modifier
                            .alpha(0.4f)
                            .padding(start = 5.dp))

                }
                Divider(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .padding(start = 10.dp)
                        .width(350.dp)
                        .alpha(0.4f),
                    color = Color.Gray,
                    thickness = 1.dp
                )

                Row (
                    modifier = Modifier.padding(start = 15.dp)
                ){
                    Text(text = "Reference",
                        fontSize = 15.sp,
                        modifier = Modifier.alpha(0.7f))

                    Text(text = "123456789101",
                        fontSize = 15.sp,
                        modifier = Modifier
                            .alpha(0.4f)
                            .padding(start = 180.dp))

                }
                Row (
                    modifier = Modifier.padding(top = 10.dp,start = 15.dp)
                ){
                    Text(text = "Date",
                        fontSize = 15.sp,
                        modifier = Modifier.alpha(0.7f))

                    Text(text = "20 JUL 2024 7:50 PM",
                        fontSize = 15.sp,
                        modifier = Modifier
                            .alpha(0.4f)
                            .padding(start = 180.dp))

                }

            }
        }

    }

}


@Preview
@Composable
private fun TransactionsDetailsPreview() {
    TransactionsDetails()
}