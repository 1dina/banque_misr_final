package com.example.speedotransfer.ui.screens.dashboard.components.transfer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.data.models.FavoriteListItem
import com.example.speedotransfer.ui.screens.dashboard.commonUI.CommonCard
import com.example.speedotransfer.ui.theme.Grey
import com.example.speedotransfer.ui.theme.Maroon

@Composable
fun PaymentStepScreen(
    modifier: Modifier = Modifier,
    recipientUser: FavoriteListItem,
    amountOfMoney: Int,
    onBackToHomeClick:()->Unit,
    onAddToFavoriteClick:()->Unit
) {
    val navController = rememberNavController()
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_transfer_success),
            tint = Unspecified,
            contentDescription = "Transfer Success"
        )
        Text(
            text = "Your transfer was successful",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = modifier.padding(vertical = 16.dp)
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Column {
                CommonCard(
                    destination = "From",
                    cardUser = "User Name",
                    cardAccount = "xxxx7890",
                )
                CommonCard(
                    destination = "To",
                    cardUser = recipientUser.favoriteRecipient,
                    cardAccount = recipientUser.favoriteRecipientAccount,

                    )
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_transfer_success_small),
                contentDescription = "done",
                tint = Unspecified,
                modifier = modifier
                    .align(Alignment.Center)

            )
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Transfer amount", fontSize = 16.sp, color = Grey)
            Text(text = "$amountOfMoney EGP", color = Grey, fontSize = 16.sp)
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
        Button(
            onClick = {onBackToHomeClick()},
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Maroon, contentColor = White)
        ) {
            Text(text = "Back to Home", fontSize = 16.sp, modifier = modifier.padding(8.dp))
        }
        OutlinedButton(
            onClick = {onAddToFavoriteClick()},
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(color = Maroon, width = 1.dp)
        ) {
            Text(
                text = "Add to Favourite",
                color = Maroon,
                fontSize = 16.sp,
                modifier = modifier.padding(8.dp)
            )
        }

    }
}



