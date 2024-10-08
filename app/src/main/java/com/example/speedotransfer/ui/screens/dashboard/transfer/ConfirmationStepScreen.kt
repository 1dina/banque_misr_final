package com.example.speedotransfer.ui.screens.dashboard.transfer

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.data.source.remote.models.favourite.FavouriteAdditionResponse
import com.example.speedotransfer.ui.screens.dashboard.commonUI.CommonCard
import com.example.speedotransfer.ui.theme.Grey
import com.example.speedotransfer.ui.theme.Marron

@Composable
fun ConfirmationStepScreen(
    senderUser:String,
    senderAccId : Int,
    amountOfMoney: Int,
    recipientUser: FavouriteAdditionResponse,
    modifier: Modifier = Modifier,
    increaseStep: (Boolean) -> Unit

) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 8.dp)

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
                    cardUser = senderUser,
                    cardAccount = "xxxx"+senderAccId.toString().takeLast(4),
                )
                CommonCard(
                    destination = "To",
                    cardUser = recipientUser.name,
                    cardAccount = "xxxx"+recipientUser.accountId.toString().takeLast(4),

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
            onClick = { increaseStep(true)
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Marron, contentColor = White)
        ) {
            Text(text = "Confirm", fontSize = 16.sp, modifier = modifier.padding(8.dp))
        }
        OutlinedButton(
            onClick = { increaseStep(false) },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(color = Marron, width = 1.dp)
        ) {
            Text(
                text = "Previous",
                color = Marron,
                fontSize = 16.sp,
                modifier = modifier.padding(8.dp)
            )
        }

    }
}



