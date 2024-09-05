package com.example.speedotransfer.ui.screens.dashboard.components.more

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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

@Composable
fun MoreScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
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
        Column {
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
            MoreItem(leadingIcon = R.drawable.ic_help, itemText = "Help") {}
            Row(
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(64.dp)
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
                    text = "Log Out",
                    fontSize = 16.sp,
                    color = Grey,
                    fontWeight = FontWeight.Medium,
                    modifier = modifier.padding(start = 8.dp)
                )

            }


        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun MoreScreenPreview() {
    MoreScreen(rememberNavController(), innerPadding = PaddingValues(8.dp))
}