package com.example.speedotransfer.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Gray
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
import com.example.speedotransfer.data.models.FavoriteListItem
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Maroon

@Composable
fun TransferScreen(navController: NavController, modifier: Modifier = Modifier) {
    var chosenUser by remember {
        mutableStateOf<FavoriteListItem?>(null)
    }
    var currentStep by remember { mutableStateOf(1) }
    var amountOfMoney by remember {
        mutableIntStateOf(0)
    }
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
    ) {
        Column {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp, start = 16.dp, end = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back), contentDescription = "",
                    modifier.weight(1f)
                )
                Text(
                    text = "Transfer",
                    fontSize = 20.sp,
                    modifier = modifier.weight(5f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = modifier.weight(1f))


            }
            Stepper(steps = 3,
                currentStep = currentStep)
            if(currentStep ==1)
                AmountStepScreen { user,amount ->
                    chosenUser = user
                    amountOfMoney = amount
                    currentStep ++

                }
            else if (currentStep ==2) ConfirmationStepScreen(amountOfMoney = amountOfMoney , recipientUser = chosenUser!!) {
                  currentStep =it
            }
            else PaymentStepScreen(navController = rememberNavController(), recipientUser =chosenUser!! , amountOfMoney =amountOfMoney )
        }



    }
}


@Composable
fun Stepper(
    steps: Int, currentStep: Int
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp)
    ) {
        for (step in 1..steps) {
            StepItem(
                step = step,
                isSelected = step <= currentStep,
               )

            if (step < steps) {
                Divider(
                    color = if (step < currentStep) Maroon else Gray,
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .width(64.dp)
                        .height(1.dp)


                )
            }
        }
    }
}

@Composable
fun StepItem(
    step: Int, isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
        ) {
            val iconResult = if (!isSelected) {
                if (step == 2) R.drawable.ic_step_two else R.drawable.ic_step_three
            } else {
                if (step == 1) R.drawable.ic_step_one
                else if (step == 2) R.drawable.ic_step_two_selected
                else R.drawable.ic_step_three_selected
            }
            Icon(
                painter = painterResource(id = iconResult),
                contentDescription = "current step ",
                tint = Unspecified
            )
        }
        Text(
            text = when (step) {
                1 -> "Amount"
                2 -> "Confirmation"
                3 -> "Payment"
                else -> "Step"
            },
            fontSize = 14.sp,
            color = if (isSelected) Maroon else Gray,
            modifier = Modifier.padding(top = 8.dp)
        )
    }

}


@Preview
@Composable
private fun TransferScreenPreview() {
    TransferScreen(rememberNavController())
}