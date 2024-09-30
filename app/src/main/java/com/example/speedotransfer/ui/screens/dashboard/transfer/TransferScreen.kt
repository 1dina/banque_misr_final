package com.example.speedotransfer.ui.screens.dashboard.transfer

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
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
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speedotransfer.R
import com.example.speedotransfer.data.source.remote.models.favourite.FavouriteAdditionResponse
import com.example.speedotransfer.data.source.remote.models.transaction.TransactionRequest
import com.example.speedotransfer.data.source.remote.retrofit.RetrofitInstance
import com.example.speedotransfer.ui.routes.AppRoutes
import com.example.speedotransfer.ui.screens.auth.signUp.IndeterminateCircularIndicator
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUI
import com.example.speedotransfer.ui.theme.LightPink
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Marron
import com.example.speedotransfer.ui.viewmodels.home.HomeUiState
import com.example.speedotransfer.ui.viewmodels.home.HomeViewModel
import com.example.speedotransfer.ui.viewmodels.home.HomeViewModelFactory

@Composable
fun TransferScreen(
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
    onNavigationCallBack: (String) -> Unit
) {
    val totalSteps = 3
    var chosenUser by remember {
        mutableStateOf(FavouriteAdditionResponse(0, ""))
    }

    var isLoading by remember {
        mutableStateOf(false)
    }
    var currentStep by remember { mutableIntStateOf(1) }
    var amountOfMoney by remember {
        mutableIntStateOf(0)
    }
    val context = LocalContext.current
    val apiService = RetrofitInstance.callable
    val viewModel: HomeViewModel =
        viewModel(factory = HomeViewModelFactory(apiService, context = context))
    val homeUiState by viewModel.uiState.collectAsState()
    val userAccountId by viewModel.accountId.collectAsState()
    val userData by viewModel.userData.collectAsState()
    LaunchedEffect(homeUiState) {

        when (homeUiState) {
            is HomeUiState.Loading -> {
                isLoading = true
            }

            is HomeUiState.Success -> {
                isLoading = false
                if ((homeUiState as HomeUiState.Success).toastMessage == "Transfer successful") {
                    if (currentStep == 2) {
                        currentStep += 1
                        sendNotification(
                            "Speedo Transfer",
                            "Your transaction is successful",
                            context
                        )
                        viewModel.succtrans = false
                    }
                }
            }

            is HomeUiState.Error -> {
                isLoading = false
                Toast.makeText(
                    context,
                    (homeUiState as HomeUiState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {}
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
                    onNavigationCallBack(AppRoutes.HOME)
                } else currentStep -= 1
            })
            Stepper(
                steps = totalSteps,
                currentStep = currentStep,
                modifier = modifier
            )
            when (currentStep) {
                1 -> AmountStepScreen { user, amount ->
                    chosenUser = user
                    amountOfMoney = amount
                    currentStep += 1

                }

                2 -> ConfirmationStepScreen(
                    senderUser = userData.name,
                    senderAccId = userAccountId,
                    amountOfMoney = amountOfMoney,
                    recipientUser = chosenUser,
                ) {
                    if (it) {
                        isLoading = true
                        viewModel.transferProcess(
                            TransactionRequest(
                                reciverAccountNum = chosenUser.accountId,
                                amount = amountOfMoney,
                                senderName = userData.name, receiverName = chosenUser.name
                            )
                        )

                    } else
                        currentStep -= 1
                }

                else -> PaymentStepScreen(
                    senderUser = userData.name,
                    senderAccountId = userAccountId,
                    recipientUser = chosenUser,
                    amountOfMoney = amountOfMoney,
                    onBackToHomeClick = {
                        onNavigationCallBack(AppRoutes.HOME)
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

    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED
    ) {

        return
    }
    NotificationManagerCompat.from(context).notify(99, builder.build())
}


