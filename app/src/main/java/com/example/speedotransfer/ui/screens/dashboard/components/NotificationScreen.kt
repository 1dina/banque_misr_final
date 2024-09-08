package com.example.speedotransfer.ui.screens.dashboard.components

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.data.DummyDataSource
import com.example.speedotransfer.data.models.dummy.NotificationItemData
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUI
import com.example.speedotransfer.ui.theme.Grey
import com.example.speedotransfer.ui.theme.LightPink
import com.example.speedotransfer.ui.theme.LightYellow

@Composable
fun NotificationScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues
) {
    val dummyData = DummyDataSource.getNotificationData()
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
            .padding(innerPadding)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            HeaderUI(headerTitle = "Notifications", onClickBackButton = {
                navController.popBackStack()
            })
            NotificationListMaker(notificationItems = dummyData)

        }
    }
}

@Composable
fun NotificationListMaker(
    notificationItems: List<NotificationItemData>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.padding(vertical = 16.dp)) {
        items(notificationItems) {
            NotificationListItem(notificationItemData = it)
        }
    }

}

@Composable
fun NotificationListItem(
    notificationItemData: NotificationItemData,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(2.dp, shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = LightPink)
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(64.dp) // Size for the box and icon
                    .shadow(
                        1.dp,
                        RoundedCornerShape(10.dp),
                        clip = false
                    ) // Apply shadow to the box
                    .background(
                        LightPink,
                        RoundedCornerShape(10.dp)
                    ) // Set the background with the same shape
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_receive),
                    contentDescription = "Receive Icon",
                    tint = Color.Unspecified, // Keeping the icon color as original
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.Center)// Ensure icon fills the box
                )
            }

            Column(
                modifier = modifier
                    .wrapContentHeight()
                    .padding(horizontal = 8.dp), verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = notificationItemData.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = notificationItemData.body,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = notificationItemData.date,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Grey
                )

            }

        }

    }

}

@Preview(showSystemUi = true)
@Composable
private fun NotificationScreenPreview() {
    NotificationScreen(navController = rememberNavController(), innerPadding = PaddingValues(16.dp))
}