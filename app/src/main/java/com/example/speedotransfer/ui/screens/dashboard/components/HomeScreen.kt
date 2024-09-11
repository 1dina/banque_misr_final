package com.example.speedotransfer.ui.screens.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.data.DummyDataSource
import com.example.speedotransfer.data.models.RecentTransactionItem
import com.example.speedotransfer.ui.theme.Grey
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Marron

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier,innerPadding : PaddingValues) {
    val transactionList = DummyDataSource.getRecentTransactionsData()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        LightYellow, LightRed
                    )
                )
            ).padding(innerPadding)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 48.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "user picture",
                    modifier = modifier
                        .size(48.dp)
                        .clip(CircleShape)                       // clip to the circle shape
                        .border(1.dp, Color.Gray, CircleShape)
                )

                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = modifier
                        .height(48.dp)
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.welcome_back),
                        color = Marron,
                        fontSize = 14.sp
                    )
                    Text(text = "User name", fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_notifications),
                    contentDescription = "notification icon",
                    modifier = modifier.padding(end = 8.dp),
                )
            }
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .height(128.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Marron, contentColor = White)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = modifier
                        .fillMaxHeight()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Current Balance ",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(text = "100000EGP", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                }
            }

            TransactionList(transactionList = transactionList)
        }

    }
}

@Composable
fun TransactionList(transactionList: List<RecentTransactionItem>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(top = 24.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Recent transactions", fontSize = 14.sp, fontWeight = FontWeight.Normal)
        Text(text = "View all", fontSize = 14.sp, color = Grey, fontWeight = FontWeight.Normal)
    }
    Surface(shape = RoundedCornerShape(4.dp), modifier = modifier.padding(top = 8.dp),
        shadowElevation = 2.dp) {
        LazyColumn {
            items(transactionList) {
                RecentTransactionUI(transactionItem = it)
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth(),
                    thickness = 0.8.dp,
                    color = Color.LightGray
                )
            }
        }
    }
}

@Composable
fun RecentTransactionUI(transactionItem: RecentTransactionItem, modifier: Modifier = Modifier) {
    Card(colors = CardDefaults.cardColors(containerColor = White), shape = RectangleShape) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp,vertical=16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_visa),
                contentDescription = "", contentScale = ContentScale.None,
                modifier = modifier.size(64.dp)
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .padding(start = 8.dp)
                    .height(64.dp)
            ) {
                Text(
                    text = transactionItem.personName,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp, modifier = modifier.weight(1f)
                )
                Text(
                    text = transactionItem.cardDetails,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp, modifier = modifier.weight(1f)
                )
                Text(
                    text = transactionItem.date,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Grey, modifier = modifier.weight(1f)
                )
            }
            Spacer(modifier = modifier.weight(1f))
            Text(text = transactionItem.price, color = Marron, fontSize = 16.sp)
        }
    }


}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(rememberNavController(), innerPadding = PaddingValues(16.dp))
}