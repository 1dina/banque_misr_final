package com.example.speedotransfer.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.Maroon

@Composable
fun AmountStepScreen(modifier: Modifier = Modifier) {
    var amountOfMoney by remember {
        mutableStateOf("0")
    }
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "How much are you sending?", fontSize = 20.sp,
            fontWeight = FontWeight.Normal, modifier = modifier.padding(16.dp)
        )
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RectangleShape,
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(8.dp)

        ) {
            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = "Amount")
                OutlinedTextField(
                    value = amountOfMoney, onValueChange = { amountOfMoney = it },
                    modifier = modifier
                        .fillMaxWidth()
                        .height(48.dp)
                )

            } }
        Row {
            Text(text = "Recipient Information")
            TextButton(onClick = { }) {
                Icon(painter = painterResource(id = R.drawable.ic_favorite), contentDescription = "favorite")
                Text(text = "Favourite", color = Maroon)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AmountStepScreenPreview() {
    AmountStepScreen()
}