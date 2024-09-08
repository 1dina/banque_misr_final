package com.example.speedotransfer.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.routes.AppRoutes
import com.example.speedotransfer.DashboardActivity
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.Maroon
import com.example.speedotransfer.ui.viewmodels.AuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speedotransfer.data.models.UserLoginRequest
import com.example.speedotransfer.data.source.BankingAPIService
import com.example.speedotransfer.ui.viewmodels.AuthViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIn(navController: NavController, modifier: Modifier = Modifier) {
    val apiService = BankingAPIService.callable
    val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(apiService))

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val toLogin by authViewModel.responseStatus.collectAsState()

    LaunchedEffect(toLogin) {
        if (toLogin) {
            authViewModel.resetResponseStatus()
            val intent = Intent(context, DashboardActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            context.startActivity(intent)
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
            modifier = Modifier.fillMaxSize()
        ) {


            Text(
                text = "Sign In",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 40.dp)

            )

            Text(
                text = stringResource(id = R.string.app),
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 32.dp),
                fontWeight = FontWeight.Medium
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(-35.dp)
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
                    label = {
                        Text(
                            text = stringResource(id = R.string.email),
                            Modifier.alpha(0.4f),
                            color = colorResource(id = R.color.black)
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .size(350.dp, 110.dp)
                        .padding(top = 40.dp),

                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = "Name",
                            Modifier
                                .alpha(0.5f)
                                .size(30.dp)
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
                    label = {
                        Text(
                            text = stringResource(id = R.string.password),
                            Modifier.alpha(0.4f),
                            color = colorResource(id = R.color.black),
                            textAlign = TextAlign.Start
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .size(350.dp, 100.dp)
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

            }

            Button(
                onClick = {
                    val userLoginRequest = UserLoginRequest(email,password)
                     authViewModel.submitLoginData(userLoginRequest)
                },
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(width = 350.dp, height = 60.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Maroon)
            ) {
                Text(
                    text = "Sign in",
                    fontSize = 18.sp
                )

            }

            Row {
                Text(
                    text = "Don't have an account?",
                    modifier = Modifier
                        .padding(top = 25.dp, start = 30.dp)
                        .alpha(0.6f),
                    color = colorResource(id = R.color.black),
                    fontSize = 15.sp
                )
                Text(
                    text = "Sign Up",
                    modifier = Modifier
                        .padding(top = 25.dp, start = 5.dp)
                        .clickable { navController.navigate(AppRoutes.FIRST_PAGE_SIGN_UP) },
                    color = Maroon,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline


                )
            }
        }
    }
}

