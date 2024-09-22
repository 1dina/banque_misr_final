package com.example.speedotransfer.ui.screens.dashboard.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.speedotransfer.R
import com.example.speedotransfer.data.source.remote.models.transaction.history.Content
import com.example.speedotransfer.data.source.remote.models.user.info.UserInfoResponse
import com.example.speedotransfer.routes.AppRoutes
import com.example.speedotransfer.ui.theme.Grey
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightWhite
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Maroon
import com.example.speedotransfer.ui.viewmodels.HomeViewModel
import com.example.speedotransfer.utils.formatDate

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    viewModel: HomeViewModel
) {

    val context = LocalContext.current
    val currentBalance by viewModel.userAccountData.collectAsState()
    val responseState by viewModel.responseStatus.collectAsState()
    val historyTransactions by viewModel.transactionHistoryList.collectAsState()
    val userData by viewModel.userInfoData.collectAsState()

    LaunchedEffect(responseState) {
        if (responseState == true)
            viewModel.resetResponseStatus()
        else {
            if (viewModel.toastMessage.value != null)
                Toast.makeText(
                    context,
                    viewModel.toastMessage.value,
                    Toast.LENGTH_SHORT
                ).show()
            viewModel.resetToastMessage()
            viewModel.resetResponseStatus()
        }

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        LightYellow, LightRed
                    )
                )
            )
            .padding(innerPadding)
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
                Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(LightWhite, shape = CircleShape),
            ) {
                val nameParts = userData.name.split(" ")
                val initials = if (nameParts.size >= 2) {
                    (nameParts[0].take(1) + nameParts[1].take(1)).uppercase()
                } else {
                    userData.name.take(2).uppercase()
                }
                Text(
                    text = initials,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .alpha(0.7f),
                    style = TextStyle(color = Color.Gray)
                )
            }
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = modifier
                        .height(48.dp)
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.welcome_back),
                        color = Maroon,
                        fontSize = 14.sp
                    )
                    Text(text = userData.name, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_notifications),
                    contentDescription = "notification icon",
                    tint = Unspecified,
                    modifier = modifier
                        .padding(end = 8.dp)
                        .clickable {
                            navController.navigate(AppRoutes.NOTIFICATIONS)
                        }
                )
            }
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .height(128.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Maroon, contentColor = White)
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
                    Text(text = currentBalance, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                }
            }

            TransactionList(transactionList = historyTransactions,userData, onAllViewClick = {
                navController.navigate(AppRoutes.TRANSACTION)
            })
        }

    }
}

@Composable
fun TransactionList(
    transactionList: List<Content>,
    userData: UserInfoResponse,
    onAllViewClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(top = 24.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Recent transactions", fontSize = 14.sp, fontWeight = FontWeight.Normal)
            Text(
                text = "View all",
                fontSize = 14.sp,
                color = Grey,
                fontWeight = FontWeight.Normal,
                modifier= modifier.clickable {
                    onAllViewClick()
                }
            )
        }
    Surface(
        shape = RoundedCornerShape(4.dp), modifier = modifier.padding(top = 8.dp),
        shadowElevation = 2.dp
    ) {
        LazyColumn {
            items(transactionList) {
                RecentTransactionUI(transactionItem = it, userData = userData )
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
fun RecentTransactionUI(transactionItem: Content, userData : UserInfoResponse, modifier: Modifier = Modifier) {

    val state = if (transactionItem.senderName == userData.name) "Sent"
    else "Received"

    val cardHolderName =
        if (transactionItem.senderName == userData.name) transactionItem.receiverName
        else transactionItem.senderName
    Card(colors = CardDefaults.cardColors(containerColor = White), shape = RectangleShape) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp)
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
                    text = cardHolderName,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp, modifier = modifier.weight(1f)
                )
                Text(
                    text = "Visa . Mater Card . 1234",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp, modifier = modifier.weight(1f)
                )
                val formattedDate = formatDate(transactionItem.createdTimeStamp)
                Text(
                    text = "$formattedDate - $state",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Grey, modifier = modifier.weight(1f)
                )
            }
            Spacer(modifier = modifier.weight(1f))
            Text(text = "${transactionItem.amount}EGP", color = Maroon, fontSize = 16.sp)
        }
    }


}