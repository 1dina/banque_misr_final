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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.data.DummyDataSource
import com.example.speedotransfer.data.models.FavoriteListItem
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUI
import com.example.speedotransfer.ui.theme.Grey
import com.example.speedotransfer.ui.theme.LightPink
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightYellow

@Composable
fun FavouriteScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val dummyData = DummyDataSource.getFavoriteRecipentData()
    Box(
        modifier = modifier
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
        Column  {
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
            FavoriteScreenListMaker(favoriteListItems = dummyData, modifier = modifier , onEditClick = {}, onDeleteClick ={

            } )

        }
    }
}

@Composable
fun FavoriteScreenListMaker(
    favoriteListItems: List<FavoriteListItem>,
    onEditClick: (FavoriteListItem) -> Unit,
    onDeleteClick: (FavoriteListItem) -> Unit,
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
    favoriteListItem: FavoriteListItem,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = LightPink),
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom =16.dp)
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
                        text = favoriteListItem.favoriteRecipient,
                        fontSize = 16.sp,
                        modifier = modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Account ${favoriteListItem.favoriteRecipientAccount}",
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


@Preview(showSystemUi = true)
@Composable
private fun FavouriteScreenPreview() {
    FavouriteScreen(rememberNavController(), innerPadding = PaddingValues(16.dp))
}