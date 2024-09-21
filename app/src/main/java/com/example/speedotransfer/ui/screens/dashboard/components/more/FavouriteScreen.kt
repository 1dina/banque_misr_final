package com.example.speedotransfer.ui.screens.dashboard.components.more

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.data.models.FavouriteAdditionResponse
import com.example.speedotransfer.data.source.remote.BankingAPIService
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUI
import com.example.speedotransfer.ui.theme.Grey
import com.example.speedotransfer.ui.theme.LightPink
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Maroon
import com.example.speedotransfer.ui.viewmodels.FavouriteViewModel
import com.example.speedotransfer.ui.viewmodels.FavouriteViewModelFactory

@Composable
fun FavouriteScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val apiService = BankingAPIService.callable
    val viewModel: FavouriteViewModel =
        viewModel(factory = FavouriteViewModelFactory(apiService, context))
    val favList  by viewModel.favListItems.collectAsState()
    var showBottomDialog by remember {
        mutableStateOf(false)
    }

    var chosenItem by remember {
        mutableStateOf(FavouriteAdditionResponse(0, ""))
    }
    if (showBottomDialog) {
        FavBottomSheetMaker(onDismiss = {
            showBottomDialog = false
        }, favoriteListItem = chosenItem)
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
        Column(modifier = modifier.padding(horizontal = 16.dp)) {
            HeaderUI(headerTitle = "Favourite", onClickBackButton = {
                navController.popBackStack()
            })
            Text(
                text = "Your favourite list",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, bottom = 16.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            FavoriteScreenListMaker(
                favoriteListItems = favList,
                modifier = modifier,
                onEditClick = {
                    showBottomDialog = true
                    chosenItem = it
                },
                onDeleteClick = {
                    chosenItem = it
                    viewModel.deleteFromFav(chosenItem.accountId)
                })

        }
    }
}

@Composable
fun FavoriteScreenListMaker(
    favoriteListItems: List<FavouriteAdditionResponse>,
    onEditClick: (FavouriteAdditionResponse) -> Unit,
    onDeleteClick: (FavouriteAdditionResponse) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxHeight()) {
        items(favoriteListItems) { item ->
            FavoriteScreenListItem(favoriteListItem = item, onEditClick = {
                onEditClick(item)
            }) {
                onDeleteClick(item)
            }

        }
    }
}

@Composable
fun FavoriteScreenListItem(
    favoriteListItem: FavouriteAdditionResponse,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = LightPink),
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Box(modifier = modifier.fillMaxWidth()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
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
                    Text(
                        text = favoriteListItem.name,
                        fontSize = 16.sp,
                        modifier = modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Account ${favoriteListItem.accountId}",
                        fontSize = 16.sp,
                        color = Grey
                    )
                }
            }
            Row(
                modifier = modifier
                    .align(Alignment.CenterEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "edit favourite item",
                    tint = Unspecified,
                    modifier = modifier
                        .padding(horizontal = 8.dp)
                        .clickable {
                            onEditClick()
                        }
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "delete favourite item",
                    tint = Unspecified,
                    modifier = modifier.clickable {
                        onDeleteClick()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavBottomSheetMaker(
    onDismiss: () -> Unit,
    favoriteListItem: FavouriteAdditionResponse,
    modifier: Modifier = Modifier
) {
    val chosenItem by remember {
        mutableStateOf(favoriteListItem)
    }
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        containerColor = White,
        modifier = modifier.wrapContentSize()
    ) {
        Column(
            modifier = modifier
                .height(500.dp)
                .padding(16.dp)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "edit sheet",
                    tint = Maroon,
                    modifier = modifier.padding(horizontal = 4.dp)
                )
                Text(text = "Edit", fontSize = 20.sp)
            }
            Text(
                text = "Recipient Account",
                modifier = modifier.padding(bottom = 8.dp, top = 16.dp),
                fontSize = 16.sp
            )
            OutlinedTextField(
                value = chosenItem.accountId.toString(),
                onValueChange = { chosenItem.accountId = it.toInt() },
                placeholder = {
                    Text(
                        text = "Enter Cardholder Account", fontSize = 14.sp, color = Grey
                    )
                },
                textStyle = TextStyle(fontSize = 14.sp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = White,
                    unfocusedContainerColor = White,
                    focusedIndicatorColor = Maroon,
                    unfocusedIndicatorColor = Grey
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .height(52.dp)
            )
            Text(
                text = "Recipient Name",
                modifier = modifier.padding(vertical = 8.dp),
                fontSize = 16.sp
            )
            OutlinedTextField(
                value = chosenItem.name,
                onValueChange = { chosenItem.name = it },
                placeholder = {
                    Text(
                        text = "Enter Cardholder Name", fontSize = 14.sp, color = Grey
                    )
                },
                textStyle = TextStyle(fontSize = 14.sp, color = Grey),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = White,
                    unfocusedContainerColor = White,
                    focusedIndicatorColor = Maroon,
                    unfocusedIndicatorColor = Grey
                ),// Input text size
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .height(52.dp)
            )
            Button(
                onClick = {
                    onDismiss()
                    //update saved item data
                },
                modifier = modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Maroon,
                    contentColor = White
                ),
                shape = RoundedCornerShape(6.dp),
            ) {
                Text(
                    text = "Save",
                    fontSize = 16.sp,
                    modifier = modifier.padding(8.dp),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun FavouriteScreenPreview() {
    FavouriteScreen(rememberNavController(), innerPadding = PaddingValues(16.dp))
}