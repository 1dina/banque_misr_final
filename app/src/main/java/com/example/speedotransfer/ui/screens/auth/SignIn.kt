package com.example.speedotransfer.ui.screens.auth

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.data.models.user.UserLoginRequest
import com.example.speedotransfer.data.source.remote.BankingAPIService
import com.example.speedotransfer.routes.AppRoutes
import com.example.speedotransfer.ui.screens.dashboard.DashboardActivity
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.Maroon
import com.example.speedotransfer.ui.viewmodels.AuthViewModel
import com.example.speedotransfer.ui.viewmodels.AuthViewModelFactory


@Composable
fun SignIn(navController: NavController, modifier: Modifier = Modifier) {
    val apiService = BankingAPIService.callable
    val context = LocalContext.current

    val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(apiService,context))

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val toLogin by authViewModel.responseStatus.collectAsState()
    var isButtonEnabled by remember {
        mutableStateOf(false)
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    isButtonEnabled = !(email.isBlank() || password.isBlank())
    LaunchedEffect(toLogin) {
        if (toLogin == true) {
            isLoading = false
            authViewModel.resetResponseStatus()
            val intent = Intent(context, DashboardActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            context.startActivity(intent)
        } else {
            isLoading = false
            if (authViewModel.toastMessage.value != null)
                Toast.makeText(
                    context,
                    authViewModel.toastMessage.value,
                    Toast.LENGTH_SHORT
                ).show()
            authViewModel.resetToastMessage()
            authViewModel.resetResponseStatus()
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        LightRed
                    )
                )
            )
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {


            Text(
                text = "Sign In",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 42.dp)

            )

            Text(
                text = stringResource(id = R.string.app),
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 64.dp),
                fontWeight = FontWeight.Medium
            )

            Column(
                verticalArrangement = Arrangement.spacedBy((-35).dp)
            ) {

                Text(
                    text = "Email",
                    modifier = Modifier
                        .padding(top = 50.dp),
                    fontSize = 16.sp
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { newText -> email = newText },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.email),
                            Modifier.alpha(0.4f),
                            color = colorResource(id = R.color.black)
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ), maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),

                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = "Name",
                            Modifier
                                .alpha(0.5f)
                                .size(24.dp)
                        )
                    }
                )

                Text(
                    text = "Password",
                    modifier = Modifier
                        .padding(top = 50.dp),
                    fontSize = 16.sp
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { newText -> password = newText },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.password),
                            Modifier.alpha(0.4f),
                            color = colorResource(id = R.color.black),
                            textAlign = TextAlign.Start
                        )
                    },maxLines = 1,
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        Icon(
                            painter = if (!isPasswordVisible) painterResource(id = R.drawable.ic_visibility)
                            else painterResource(id = R.drawable.ic_eye),
                            contentDescription = "Password",
                            Modifier
                                .alpha(0.5f)
                                .size(24.dp)
                                .clickable {
                                    isPasswordVisible = !isPasswordVisible
                                }
                        )
                    }


                )

            }

            Button(
                onClick = {
                    isLoading = true
                    val userLoginRequest = UserLoginRequest(email, password)
                    authViewModel.submitLoginData(userLoginRequest)
                },
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(),
                enabled = isButtonEnabled,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Maroon)
            ) {
                Text(
                    text = "Sign in",
                    fontSize = 16.sp,
                    modifier = modifier.padding(10.dp)
                )

            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                Text(
                    text = "Don't have an account?",
                    modifier = Modifier
                        .alpha(0.6f),
                    color = colorResource(id = R.color.black),
                    fontSize = 16.sp
                )
                Text(
                    text = "Sign Up",
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .clickable {
                            navController.navigate(AppRoutes.FIRST_PAGE_SIGN_UP) {
                                popUpTo(AppRoutes.SIGN_IN) { inclusive = true }
                            }
                        },
                    color = Maroon,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline


                )
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


@Preview
@Composable
private fun SignInPreview() {
    SignIn(navController = rememberNavController())
}