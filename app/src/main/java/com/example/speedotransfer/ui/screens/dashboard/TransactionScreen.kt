package com.example.speedotransfer.ui.screens.dashboard

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.speedotransfer.R
import com.example.speedotransfer.data.models.Content
import com.example.speedotransfer.routes.AppRoutes
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUI
import com.example.speedotransfer.ui.theme.Green
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Marron
import com.example.speedotransfer.ui.theme.TransparentGreen
import com.example.speedotransfer.ui.viewmodels.HomeViewModel
import com.example.speedotransfer.utils.formatDate


@Composable
fun TransactionScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val transactionList = viewModel.transactionHistoryList.collectAsState()
    Log.e("trace list ",transactionList.value.toString())
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
            .padding(innerPadding)
    ) {
        Column(modifier = modifier.padding(horizontal = 16.dp)) {
            HeaderUI("Transactions", onClickBackButton = {
                navController.popBackStack()
            })
            Text(
                text = "Your Last Transactions",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

            CardTransactionList(transactionList = transactionList.value, onClickItem = { it ->
                navController.navigate(
                    "${AppRoutes.TRANS_DETAILS}/${it.receiverName}/${it.amount}/${it.createdTimeStamp}" +
                            "/${it.reciverAccountId}")
            }, modifier)
        }
    }

}

@Composable
fun CardTransactionList(
    transactionList: List<Content>,
    onClickItem: (Content) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
            .wrapContentHeight()
            .padding(top = 24.dp)
    ) {
        items(transactionList) {
            CardTransactionItemUI(transactionCard = it) {
                onClickItem(it)
            }
        }
    }
}

@Composable
fun CardTransactionItemUI(
    transactionCard: Content, onClickItem: (Content) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .height(148.dp),
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
                    Icon(
                        painter = painterResource(id = R.drawable.ic_visa_transaction),
                        contentDescription = "Visa Image",
                        modifier = Modifier
                            .fillMaxSize(),
                        tint = Color.Unspecified
                    )
                }
            }
            Column(
                modifier =
                Modifier
                    .padding(top = 15.dp, start = 10.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = transactionCard.receiverName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Visa . Mater Card . 1234",
                    fontSize = 12.sp,
                    modifier = Modifier.alpha(0.8f)
                )
                val formattedDate = formatDate(transactionCard.createdTimeStamp)
                Text(
                    text = "${formattedDate} - Received",
                    fontSize = 12.sp,
                    modifier = Modifier.alpha(0.6f)
                )

                Text(
                    text = "$${transactionCard.amount}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(top = 16.dp),
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
                        .clickable {
                            onClickItem(transactionCard)
                        }
                )

                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(end = 10.dp, top = 20.dp)
                        .background(
                            color = TransparentGreen,
                            shape = RoundedCornerShape(8.dp)
                        )

                ) {

                    Text(
                        text = "Successful",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 8.dp, vertical = 2.dp),
                        style = TextStyle(color = Green)
                    )

                }

            }
        }

    }

}
