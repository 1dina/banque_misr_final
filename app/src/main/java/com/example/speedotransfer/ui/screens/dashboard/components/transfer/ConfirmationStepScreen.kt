package com.example.speedotransfer.ui.screens.dashboard.components.transfer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.data.models.FavoriteListItem
import com.example.speedotransfer.ui.screens.dashboard.commonUI.CommonCard
import com.example.speedotransfer.ui.theme.Grey
import com.example.speedotransfer.ui.theme.Maroon

@Composable
fun ConfirmationStepScreen(
    modifier: Modifier = Modifier,
    amountOfMoney: Int,
    recipientUser: FavoriteListItem,
    currentStep: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "$amountOfMoney EGP", modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Center, fontSize = 20.sp, fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Transfer amount", modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            textAlign = TextAlign.Center,
            color = Grey,
            fontSize = 16.sp
        )
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total amount", fontSize = 16.sp)
            Text(text = "$amountOfMoney EGP", fontSize = 16.sp, fontWeight = FontWeight.Light)
        }
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
                painter = painterResource(id = R.drawable.ic_from_to),
                contentDescription = "to",
                tint = Unspecified,
                modifier = modifier
                    .align(Alignment.Center)

            )
        }
        Button(
            onClick = { currentStep(3) },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Maroon, contentColor = White)
        ) {
            Text(text = "Confirm", fontSize = 16.sp, modifier = modifier.padding(8.dp))
        }
        OutlinedButton(
            onClick = { currentStep(1) },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(color = Maroon, width = 1.dp)
        ) {
            Text(
                text = "Previous",
                color = Maroon,
                fontSize = 16.sp,
                modifier = modifier.padding(8.dp)
            )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun ConfirmationStepScreenPreview() {
    ConfirmationStepScreen(
        amountOfMoney = 100,
        recipientUser = FavoriteListItem("Dina Fadel", "xxxx7890"), currentStep = {

        })
}