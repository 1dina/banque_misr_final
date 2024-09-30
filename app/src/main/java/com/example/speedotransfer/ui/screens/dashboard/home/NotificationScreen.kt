package com.example.speedotransfer.ui.screens.dashboard.home

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedotransfer.R
import com.example.speedotransfer.data.source.remote.models.transaction.history.Content
import com.example.speedotransfer.data.source.remote.models.user.info.UserInfoResponse
import com.example.speedotransfer.data.source.remote.retrofit.RetrofitInstance
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUI
import com.example.speedotransfer.ui.theme.Grey
import com.example.speedotransfer.ui.theme.LightPink
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.viewmodels.fav.FavouriteUiState
import com.example.speedotransfer.ui.viewmodels.home.HomeUiState
import com.example.speedotransfer.ui.viewmodels.home.HomeViewModel
import com.example.speedotransfer.ui.viewmodels.home.HomeViewModelFactory
import com.example.speedotransfer.utils.formatDate

@Composable
fun NotificationScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
) {
    val context = LocalContext.current
    val apiService = RetrofitInstance.callable
    val viewModel: HomeViewModel =
        viewModel(factory = HomeViewModelFactory(apiService, context = context))
    val homeUiState by viewModel.uiState.collectAsState()
    val transactionList = when (homeUiState) {
        is HomeUiState.Success -> (homeUiState as HomeUiState.Success).transactionHistory
        else -> emptyList()
    }
    val userData = when (homeUiState) {
        is HomeUiState.Success -> (homeUiState as HomeUiState.Success).userInfo
        else -> null
    }
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
            if (userData != null) {
                NotificationListMaker(
                    notificationItems = transactionList,
                    userData = userData
                )
            }

        }
    }
}

@Composable
fun NotificationListMaker(
    notificationItems: List<Content>,
    modifier: Modifier = Modifier,
    userData: UserInfoResponse
) {
    LazyColumn(modifier = modifier.padding(vertical = 16.dp)) {
        items(notificationItems) {
            NotificationListItem(notificationItemData = it, userData)
        }
    }

}

@Composable
fun NotificationListItem(
    notificationItemData: Content,
    userData: UserInfoResponse,
    modifier: Modifier = Modifier
) {
    val state = if (notificationItemData.senderName == userData.name) "Sent"
    else "Received"
    val title = if (notificationItemData.senderName == userData.name) "Send"
    else "Receive"
    val cardHolderName =
        if (notificationItemData.senderName == userData.name) notificationItemData.receiverName
        else notificationItemData.senderName
    val cardHolderAccount =
        if (notificationItemData.senderName == userData.name) notificationItemData.reciverAccountId
        else notificationItemData.senderAccountId
    val decideDestination = if (state == "Sent") "to"
    else "from"
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
                    .size(64.dp)
                    .shadow(
                        1.dp,
                        RoundedCornerShape(10.dp),
                        clip = false
                    )
                    .background(
                        LightPink,
                        RoundedCornerShape(10.dp)
                    )
            ) {
                val rotateDegree = if (state == "Sent") 180.0f
                else 0.0f
                Icon(
                    painter = painterResource(id = R.drawable.ic_receive),
                    contentDescription = "Receive Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.Center)
                        .rotate(rotateDegree)

                )
            }

            Column(
                modifier = modifier
                    .wrapContentHeight()
                    .padding(horizontal = 8.dp), verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "$title Transaction",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "You have $state ${notificationItemData.amount} EGP $decideDestination $cardHolderName " +
                            "xxxx" + cardHolderAccount.toString().takeLast(4),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
                val formattedDate = formatDate(notificationItemData.createdTimeStamp)

                Text(
                    text = formattedDate,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Grey
                )

            }

        }

    }

}

