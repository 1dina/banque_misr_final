package com.example.speedotransfer.ui.screens.dashboard.components.mycards

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedotransfer.data.models.account.UserAccountsResponseItem
import com.example.speedotransfer.data.models.user.info.UserInfoResponse
import com.example.speedotransfer.data.source.remote.retrofit.RetrofitInstance
import com.example.speedotransfer.ui.screens.dashboard.commonUI.CommonCard
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUI
import com.example.speedotransfer.ui.theme.LightPink
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Maroon
import com.example.speedotransfer.ui.theme.MediumGrey
import com.example.speedotransfer.ui.viewmodels.HomeViewModel
import com.example.speedotransfer.ui.viewmodels.MyCardsViewModel
import com.example.speedotransfer.ui.viewmodels.MyCardsViewModelFactory

@Composable
fun MyCardScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    homeViewModel : HomeViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val apiService = RetrofitInstance.callable
    val viewModel: MyCardsViewModel =
        viewModel(factory = MyCardsViewModelFactory(apiService, context))
    val cardList by viewModel.myCardsList.collectAsState()
    val isCardCreated by viewModel.responseStatus.collectAsState()
    val userData = homeViewModel.userInfoData.collectAsState()
    LaunchedEffect(isCardCreated) {
        if (isCardCreated == true) {
            viewModel.resetResponseStatus()
        } else {
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
                MyCardsListMaker(myCardsList = cardList, modifier = modifier.weight(2f), userData = userData.value)

                Button(
                    onClick = { viewModel.createAccount() },
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
fun MyCardsListMaker(myCardsList: List<UserAccountsResponseItem>, userData: UserInfoResponse, modifier: Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        itemsIndexed(myCardsList) { index, item ->
            MyCardsListItemUI(myCardsListItem = item, index = index , userData)
        }
    }
}

@Composable
fun MyCardsListItemUI(
    myCardsListItem: UserAccountsResponseItem,
    index: Int,
    userData: UserInfoResponse,
    modifier: Modifier = Modifier
) {
    Box {
        CommonCard(
            destination = "",
            cardUser = userData.name,
            cardAccount = "Account xxxx${myCardsListItem.id.toString().takeLast(4)}"
        )
        if (index == 0)

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

