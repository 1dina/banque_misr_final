package com.example.speedotransfer.ui.screens.dashboard.components.mycards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.data.DummyDataSource
import com.example.speedotransfer.data.models.dummy.MyCardsItemData
import com.example.speedotransfer.routes.AppRoutes
import com.example.speedotransfer.ui.screens.dashboard.commonUI.CommonCard
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUI
import com.example.speedotransfer.ui.theme.LightPink
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Maroon
import com.example.speedotransfer.ui.theme.MediumGrey

@Composable
fun MyCardScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val myCardsInfo = DummyDataSource.getCardInfo()
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
            HeaderUI(headerTitle = "My Cards", onClickBackButton = { navController.popBackStack() })
            Column(modifier = modifier.fillMaxSize()) {
                MyCardsListMaker(myCardsList = myCardsInfo, modifier = modifier.weight(2f))

                Button(
                    onClick = { navController.navigate(AppRoutes.CURRENCY) },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 64.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Maroon,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Add New Account",
                        fontSize = 16.sp,
                        modifier = modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MyCardsListMaker(myCardsList: List<MyCardsItemData>, modifier: Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        items(myCardsList) {
            MyCardsListItemUI(myCardsListItem = it)
        }
    }
}

@Composable
fun MyCardsListItemUI(myCardsListItem: MyCardsItemData, modifier: Modifier = Modifier) {
    Box {
        CommonCard(
            destination = "",
            cardUser = myCardsListItem.cardName,
            cardAccount = "Account ${myCardsListItem.cardAccount}"
        )
        if (myCardsListItem.isDefault)

            Surface(
                color = MediumGrey,
                shape = RoundedCornerShape(8.dp),
                modifier = modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 32.dp, end = 16.dp)
            ) {
                Text(
                    text = "Default", fontSize = 11.sp,
                    modifier = modifier.padding(vertical = 1.dp, horizontal = 10.dp)
                )
            }


    }
}


@Preview(showSystemUi = true)
@Composable
private fun MyCardScreenPreview() {
    MyCardScreen(rememberNavController(), PaddingValues())
}