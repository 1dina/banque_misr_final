package com.example.speedotransfer.ui.screens.dashboard.components.mycards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUIWithCancel
import com.example.speedotransfer.ui.theme.Grey
import com.example.speedotransfer.ui.theme.LightPink
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Maroon

@Composable
fun CardAddition(navController: NavController, modifier: Modifier = Modifier) {
    var cardHolderName by remember {
        mutableStateOf("")
    }
    var cardNumber by remember {
        mutableStateOf("")
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
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            HeaderUIWithCancel(
                headerTitle = "Add Card",
                headerOption = "Cancel",
                onClickBackButton = { navController.popBackStack() },
                onOptionClick = { navController.popBackStack() },
                modifier
            )
            Text(
                text = "Sign on your Speedo Transfer Account",
                modifier = modifier
                    .padding(end = 8.dp, start = 8.dp, top = 32.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Cardholder Name",
                modifier = modifier.padding(vertical = 8.dp),
                fontSize = 16.sp
            )
            OutlinedTextField(value = cardHolderName,
                onValueChange = { cardHolderName = it },
                placeholder = {
                    Text(
                        text = "Enter Cardholder Name", fontSize = 14.sp, color = Grey
                    )
                }, // Placeholder text size
                textStyle = TextStyle(fontSize = 14.sp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Maroon,
                    unfocusedIndicatorColor = Grey
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .height(52.dp)
            )
            Text(
                text = "Card NO", modifier = modifier.padding(vertical = 8.dp), fontSize = 16.sp
            )
            OutlinedTextField(value = cardNumber,
                onValueChange = { cardNumber = it },
                placeholder = {
                    Text(
                        text = "Card NO", fontSize = 14.sp, color = Grey
                    )
                }, // Placeholder text size
                textStyle = TextStyle(fontSize = 14.sp, color = Grey),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Maroon,
                    unfocusedIndicatorColor = Grey
                ),// Input text size
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .height(52.dp)
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                Column(modifier = modifier.weight(1f)) {
                    Text(
                        text = "MM/YY", modifier = modifier.padding(bottom = 8.dp), fontSize = 16.sp
                    )
                    OutlinedTextField(value = cardHolderName,
                        onValueChange = { cardHolderName = it },
                        placeholder = {
                            Text(
                                text = "MM/YY", fontSize = 14.sp, color = Grey
                            )
                        }, // Placeholder text size
                        textStyle = TextStyle(fontSize = 14.sp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Maroon,
                            unfocusedIndicatorColor = Grey
                        ),
                        modifier = modifier.height(52.dp)
                    )
                }
                Column(
                    modifier = modifier
                        .padding(start = 8.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "CVV", modifier = modifier.padding(bottom = 8.dp), fontSize = 16.sp

                    )
                    OutlinedTextField(value = cardNumber,
                        onValueChange = { cardNumber = it },
                        placeholder = {
                            Text(
                                text = "CVV", fontSize = 14.sp, color = Grey
                            )
                        }, // Placeholder text size
                        textStyle = TextStyle(fontSize = 14.sp, color = Grey),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Maroon,
                            unfocusedIndicatorColor = Grey
                        ),// Input text size
                        modifier = modifier.height(52.dp)
                    )
                }
            }

            Button(
                onClick = {},
                modifier = modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Maroon, contentColor = Color.White
                ),
                shape = RoundedCornerShape(6.dp),
            ) {
                Text(text = "Sign on", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }


        }

    }

}