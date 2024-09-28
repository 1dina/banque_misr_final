package com.example.speedotransfer.ui.screens.dashboard.commonUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.LightWhite
import com.example.speedotransfer.ui.theme.Marron


@Composable
fun TransferProcessCard(
    destination: String,
    cardUser: String,
    cardAccount: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(top = 8.dp)
            .height(120.dp),
        colors = CardDefaults.cardColors(containerColor = LightWhite)
    ) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bank),
                contentDescription = "icon bank",
                modifier = Modifier
                    .size(50.dp),
            )
            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(start = 32.dp), verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = destination, color = Marron, fontSize = 16.sp)
                Text(
                    text = cardUser,
                    color = Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = cardAccount,
                    color = DarkGray,
                    fontSize = 16.sp
                )
            }

        }

    }
}