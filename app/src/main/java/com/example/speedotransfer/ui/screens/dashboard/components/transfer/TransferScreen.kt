package com.example.speedotransfer.ui.screens.dashboard.components.transfer

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.speedotransfer.R
import com.example.speedotransfer.data.models.FavouriteAddition
import com.example.speedotransfer.data.models.TransactionRequest
import com.example.speedotransfer.routes.AppRoutes
import com.example.speedotransfer.ui.screens.auth.IndeterminateCircularIndicator
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUI
import com.example.speedotransfer.ui.theme.LightPink
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Marron
import com.example.speedotransfer.ui.viewmodels.HomeViewModel

@Composable
fun TransferScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    var chosenUser by remember {
        mutableStateOf(FavouriteAddition(0, ""))
    }

    var isLoading by remember {
        mutableStateOf(false)
    }
    var currentStep by remember { mutableIntStateOf(1) }
    var amountOfMoney by remember {
        mutableIntStateOf(0)
    }
    val context = LocalContext.current
    val transferUserFound by viewModel.userFound.collectAsState()
    LaunchedEffect(transferUserFound) {
        isLoading = false
        if (transferUserFound == true) {
            if (currentStep == 1) {
                currentStep += 1
            }
            viewModel.resetResponseStatus()
        } else {
            if (viewModel.toastMessage.value != null)
                Toast.makeText(
                    context,
                    viewModel.toastMessage.value,
                    Toast.LENGTH_SHORT
                ).show()
            viewModel.resetToastMessage()
            viewModel.resetResponseStatus()
        }
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
        Column(modifier = modifier.padding(horizontal = 16.dp)) {

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
            when (currentStep) {
                1 -> AmountStepScreen { user, amount ->
                    chosenUser = user
                    amountOfMoney = amount
                    isLoading = true
                    viewModel.transferProcess(
                        TransactionRequest(
                            reciverAccountNum = user.accountId,
                            amount = amount,
                            senderName = "userName", receiverName = user.name
                        )
                    )
                }

                2 -> ConfirmationStepScreen(
                    amountOfMoney = amountOfMoney,
                    recipientUser = chosenUser!!
                ) {
                    currentStep = it
                    if (currentStep == 3 && viewModel.succtrans == true) {
                        sendNotification("Notification", "Your transaction is successful", context)
                        viewModel.succtrans = false
                    }


                }

                else -> PaymentStepScreen(
                    recipientUser = chosenUser!!,
                    amountOfMoney = amountOfMoney,
                    onBackToHomeClick = {
                        navController.navigate(AppRoutes.HOME) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                inclusive = true
                            }
                        }
                    }) {
                    viewModel.addToFav(chosenUser)
                }


            }
            if (isLoading) {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                        .wrapContentSize(Alignment.Center)
                ) {
                    IndeterminateCircularIndicator()
                }
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
                HorizontalDivider(
                    modifier = modifier
                        .padding(top = 24.dp)
                        .width(64.dp)
                        .height(1.dp),
                    color = if (step < currentStep) Marron else Gray
                )
            }
        }
    }
}

@Composable
fun StepItem(
    step: Int, isSelected: Boolean, modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .size(48.dp)
        ) {
            val iconResult = if (!isSelected) {
                if (step == 2) R.drawable.ic_step_two else R.drawable.ic_step_three
            } else {
                when (step) {
                    1 -> R.drawable.ic_step_one
                    2 -> R.drawable.ic_step_two_selected
                    else -> R.drawable.ic_step_three_selected
                }
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

fun createNotificationChannel(context: Context) {
    val name = "Notification Channel"
    val importance = NotificationManager.IMPORTANCE_DEFAULT
    val channel = NotificationChannel("1", name, importance).apply {
        description = "Scheduled notification"
    }
    val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    manager.createNotificationChannel(channel)
}

fun sendNotification(title: String, text: String, context: Context) {
    createNotificationChannel(context)

    val builder = Notification.Builder(context, "1")
        .setContentTitle(title)
        .setContentText(text)
        .setAutoCancel(true)
        .setSmallIcon(R.drawable.ic_succtrans)

    NotificationManagerCompat.from(context).notify(99, builder.build())
}


