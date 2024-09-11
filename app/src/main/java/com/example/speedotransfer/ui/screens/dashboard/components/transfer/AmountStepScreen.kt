package com.example.speedotransfer.ui.screens.dashboard.components.transfer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.data.DummyDataSource
import com.example.speedotransfer.data.models.FavoriteListItem
import com.example.speedotransfer.ui.theme.Grey
import com.example.speedotransfer.ui.theme.LightPink
import com.example.speedotransfer.ui.theme.Marron

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmountStepScreen(modifier: Modifier = Modifier,recipientUserChosen : (FavoriteListItem,Int) -> Unit) {
    var amountOfMoney by remember {
        mutableStateOf("0")
    }
    var recipientName by remember {
        mutableStateOf("")
    }
    var recipientAccount by remember {
        mutableStateOf("")
    }

    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    if (showBottomSheet) BottomSheetFav(onDismiss = {showBottomSheet =it} ) {

        recipientName = it.favoriteRecipient
        recipientAccount = it.favoriteRecipientAccount
        showBottomSheet =false
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = "How much are you sending?", fontSize = 20.sp, fontWeight = FontWeight.Normal
        )
        Card(
            colors = CardDefaults.cardColors(containerColor = White),
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .height(150.dp)
        ) {
            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Amount", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                OutlinedTextField(
                    value = amountOfMoney,
                    onValueChange = {  amountOfMoney = it }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Marron,
                        unfocusedIndicatorColor = Grey, focusedContainerColor = White,
                        unfocusedContainerColor = White,
                    ),
                    placeholder = {
                        Text(
                            text = "Enter Amount", fontSize = 18.sp
                        )
                    }, // Placeholder text size
                    textStyle = TextStyle(fontSize = 18.sp) // Input text size
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = "Recipient Information", fontSize = 16.sp)
            TextButton(onClick = { showBottomSheet = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    contentDescription = "favorite",
                    tint = Marron
                )
                Text(text = "Favourite", color = Marron)
                Icon(
                    painter = painterResource(id = R.drawable.ic_drop_down),
                    contentDescription = "drop down",
                    tint = Marron
                )
            }
        }
        Text(
            text = "Recipient Name", modifier = modifier.padding(vertical = 8.dp), fontSize = 16.sp
        )
        OutlinedTextField(
            value = recipientName,
            onValueChange = { recipientName = it },
            placeholder = {
                Text(
                    text = "Enter Recipient Name", fontSize = 14.sp, color = Grey
                )
            }, // Placeholder text size
            textStyle = TextStyle(fontSize = 14.sp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = White,
                unfocusedContainerColor = White,
                focusedIndicatorColor = Marron,
                unfocusedIndicatorColor = Grey
            ),
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp)
        )
        Text(
            text = "Recipient Account",
            modifier = modifier.padding(vertical = 8.dp),
            fontSize = 16.sp
        )
        OutlinedTextField(
            value = recipientAccount,
            onValueChange = { recipientAccount = it },
            placeholder = {
                Text(
                    text = "Enter Recipient Account", fontSize = 14.sp, color = Grey
                )
            }, // Placeholder text size
            textStyle = TextStyle(fontSize = 14.sp, color = Grey),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = White,
                unfocusedContainerColor = White,
                focusedIndicatorColor = Marron,
                unfocusedIndicatorColor = Grey
            ),// Input text size
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .height(52.dp)
        )
        Button(
            onClick = {
                val userChosen = FavoriteListItem(recipientName,recipientAccount)
                recipientUserChosen(userChosen,amountOfMoney.toInt())
            },
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Marron, contentColor = White),
            shape = RoundedCornerShape(6.dp),
        ) {
            Text(text = "Continue", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetFav(modifier: Modifier = Modifier , onDismiss : (Boolean)->Unit,onItemClicked: (FavoriteListItem) -> Unit) {
    val favoriteList = DummyDataSource.getFavoriteRecipentData()
    ModalBottomSheet(onDismissRequest = { onDismiss(false)}) {
        Column(modifier = modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    contentDescription = "Favorite list",
                    modifier = modifier.padding(horizontal = 8.dp),
                    tint = Marron
                )
                Text(
                    text = "Favourite List",
                    color = Marron,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            FavoriteListMaker(favItems = favoriteList, modifier = modifier){
                onItemClicked(it)
            }
        }
    }
}

@Composable
fun FavoriteListMaker(favItems: List<FavoriteListItem>, modifier: Modifier,selectedItem : (FavoriteListItem) -> Unit) {
    LazyColumn(modifier = modifier.padding(top = 24.dp)) {
        items(favItems) { item ->
            FavoriteListItemUI(item, onItemClicked = {
                selectedItem(item)
            })
        }
    }
}

@Composable
fun FavoriteListItemUI(favoriteListItem: FavoriteListItem, modifier: Modifier = Modifier, onItemClicked:(FavoriteListItem)->Unit) {
    Card(
        onClick = { onItemClicked(favoriteListItem)},
        colors = CardDefaults.cardColors(containerColor = LightPink),
        modifier = modifier.padding(bottom = 8.dp)
    ) {
        Row( verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(80.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bank),
                contentDescription = "bank",
                tint = Unspecified
            )
            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = favoriteListItem.favoriteRecipient, fontSize = 16.sp,modifier = modifier.padding(bottom = 4.dp))
                Text(
                    text = "Account ${favoriteListItem.favoriteRecipientAccount}",
                    fontSize = 16.sp,
                    color = Grey
                )
            }
        }
    }
}

