package com.example.speedotransfer.ui.screens.dashboard


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.data.models.transaction.history.Passwords
import com.example.speedotransfer.data.source.remote.retrofit.RetrofitInstance
import com.example.speedotransfer.ui.screens.auth.Texts
import com.example.speedotransfer.ui.screens.dashboard.commonUI.HeaderUI
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Maroon
import com.example.speedotransfer.ui.viewmodels.HomeViewModel
import com.example.speedotransfer.ui.viewmodels.HomeViewModelFactory


@Composable
fun ChangePassword(navController: NavController, modifier: Modifier = Modifier) {

    val apiService = RetrofitInstance.callable
    val context = LocalContext.current

    val homeViewModel: HomeViewModel =
        viewModel(factory = HomeViewModelFactory(apiService, context))

    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isPasswordVisible1 by remember { mutableStateOf(false) }

    var isButtonEnabled by remember {
        mutableStateOf(false)
    }

    isButtonEnabled = !(currentPassword.isBlank() || newPassword.isBlank())





    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        LightYellow,
                        LightRed
                    )
                )
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            HeaderUI(
                headerTitle = "Change Password",
                onClickBackButton = { navController.popBackStack() })
            Column(
                verticalArrangement = Arrangement.spacedBy((-25).dp),
                modifier = Modifier.fillMaxHeight()
            ) {


                Texts("Current password")

                OutlinedTextField(
                    value = currentPassword,
                    onValueChange = { newText -> currentPassword = newText },
                    label = {
                        Text(
                            text = "Enter your password",
                            Modifier.alpha(0.4f),
                            color = colorResource(id = R.color.black),
                            textAlign = TextAlign.Start
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_eye),
                            contentDescription = "Password isn't visible",
                            Modifier
                                .alpha(0.5f)
                                .size(30.dp)
                                .clickable {
                                    isPasswordVisible = !isPasswordVisible

                                }
                        )
                    }


                )

                Texts("New password")

                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { newText -> newPassword = newText },
                    placeholder = {
                        Text(
                            text = "Enter your password",
                            Modifier.alpha(0.4f),
                            color = colorResource(id = R.color.black),
                            textAlign = TextAlign.Start
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                    visualTransformation = if (isPasswordVisible1) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_eye),
                            contentDescription = "Password isn't visible",
                            Modifier
                                .alpha(0.5f)
                                .size(30.dp)
                                .clickable {
                                    isPasswordVisible1 = !isPasswordVisible1

                                }
                        )
                    }


                )


                Button(
                    onClick = {
                        val updatePassword = Passwords(currentPassword, newPassword)
                        homeViewModel.updatePassword(updatePassword)
                    },
                    modifier = Modifier
                        .padding(top = 64.dp)
                        .fillMaxWidth(),
                    enabled = isButtonEnabled,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Maroon)
                ) {
                    Text(
                        text = "Save", fontSize = 16.sp, modifier = modifier.padding(10.dp)
                    )

                }
            }

        }
    }


}


@Preview
@Composable
private fun ChangePasswordPreview() {

    ChangePassword(rememberNavController())

}