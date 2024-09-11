package com.example.speedotransfer.ui.screens.dashboard.components.more

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.routes.AppRoutes
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUI
import com.example.speedotransfer.ui.screens.dashboard.commonUI.MoreItem
import com.example.speedotransfer.ui.theme.Grey
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Marron

@Composable
fun MoreScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    var showBottomDialog by remember {
        mutableStateOf(false)
    }
    if (showBottomDialog) HelpBottomSheetMaker(
        onDismiss = { showBottomDialog = false },
        onCallClick = { showBottomDialog = false },
        onEmailClick = { showBottomDialog = false })
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
        Column(modifier = modifier.padding(horizontal = 16.dp)) {
            HeaderUI(headerTitle = "More", onClickBackButton = {
                navController.navigate(AppRoutes.HOME) {
                    popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                }
            })
            Spacer(modifier = modifier.height(32.dp))
            MoreItem(leadingIcon = R.drawable.ic_website, itemText = "Transfer From Website") {}
            MoreItem(leadingIcon = R.drawable.ic_favorite, itemText = "Favourites") {
                navController.navigate(AppRoutes.FAVOURITES)
            }
            MoreItem(leadingIcon = R.drawable.ic_user, itemText = "Profile") {}
            MoreItem(leadingIcon = R.drawable.ic_help, itemText = "Help") {
                showBottomDialog = true
            }
            Row(
                modifier = modifier

                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(8.dp)
                    .clickable { //here to return to loginScreen
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logout),
                    contentDescription = "Log out",
                    tint = Grey,
                    modifier = modifier.size(24.dp)
                )
                Text(
                    text = "Logout",
                    fontSize = 16.sp,
                    color = Grey,
                    fontWeight = FontWeight.Medium,
                    modifier = modifier.padding(start = 8.dp)
                )

            }


        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpBottomSheetMaker(
    onDismiss: () -> Unit,
    onCallClick: () -> Unit,
    onEmailClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        containerColor = White,
        modifier = modifier.wrapContentSize()
    ) {
        Row(
            modifier = modifier
                .height(280.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = White), modifier = modifier
                    .width(120.dp)
                    .height(140.dp)
                    .clickable {
                        onEmailClick()
                    }, elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email_us),
                        contentDescription = "our email", tint = Unspecified,
                        modifier = modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Send Email",
                        fontSize = 14.sp, color = Black,
                    )

                }
            }
            Card(
                colors = CardDefaults.cardColors(containerColor = White), modifier = modifier
                    .width(120.dp)
                    .height(140.dp)
                    .clickable {
                        onCallClick()
                    }, elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_call_us),
                        contentDescription = "our phone number", tint = Unspecified,
                        modifier = modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Call Us",
                        fontSize = 14.sp, color = Black,

                        )
                    Text(
                        text = "0000000",
                        fontSize = 14.sp,
                        color = Marron,

                        )
                }

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MoreScreenPreview() {
    MoreScreen(rememberNavController(), innerPadding = PaddingValues(8.dp))
}