package com.example.speedotransfer.ui.screens.dashboard.components.transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.data.models.FavoriteListItem
import com.example.speedotransfer.routes.AppRoutes
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUI
import com.example.speedotransfer.ui.theme.LightPink
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Marron

@Composable
fun TransferScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
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
                        LightYellow, LightYellow, LightPink, LightRed
                    )
                )
            )
            .padding(innerPadding)
    ) {
        Column(modifier = modifier.padding(horizontal =16.dp)) {

            HeaderUI(headerTitle = "Transfer", onClickBackButton = {
                if (currentStep != 2) {
                    navController.navigate(AppRoutes.HOME) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                    }
                } else currentStep -= 1
            })
            Stepper(
                steps = 3,
                currentStep = currentStep,
                modifier = modifier
            )
            if (currentStep == 1)
                AmountStepScreen { user, amount ->
                    chosenUser = user
                    amountOfMoney = amount
                    currentStep++

                }
            else if (currentStep == 2) ConfirmationStepScreen(
                amountOfMoney = amountOfMoney,
                recipientUser = chosenUser!!
            ) {
                currentStep = it
            }
            else PaymentStepScreen(
                recipientUser = chosenUser!!,
                amountOfMoney = amountOfMoney,
                onBackToHomeClick = {
                    navController.navigate(AppRoutes.HOME) {
                        popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                    }
                }) {
                //handle to add to favourite
            }

        }


    }
}


@Composable
fun Stepper(
    steps: Int, currentStep: Int,
    modifier: Modifier
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp)
    ) {
        for (step in 1..steps) {
            StepItem(
                step = step,
                isSelected = step <= currentStep,
                modifier = modifier
            )

            if (step < steps) {
                Divider(
                    color = if (step < currentStep) Marron else Gray,
                    modifier = modifier
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
    ,modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
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
            color = if (isSelected) Marron else Gray,
            modifier = modifier.padding(top = 8.dp)
        )
    }

}


@Preview
@Composable
private fun TransferScreenPreview() {
    TransferScreen(rememberNavController(), innerPadding = PaddingValues(16.dp))
}