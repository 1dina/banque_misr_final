package com.example.speedotransfer.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedotransfer.DashboardActivity
import com.example.speedotransfer.R
import com.example.speedotransfer.data.models.UserAuthRegisterRequest
import com.example.speedotransfer.data.source.BankingAPIService
import com.example.speedotransfer.routes.AppRoutes
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.Maroon
import com.example.speedotransfer.ui.viewmodels.AuthViewModel
import com.example.speedotransfer.ui.viewmodels.AuthViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp2(
    navController: NavController,
    name: String,
    email: String,
    password: String,
    modifier: Modifier = Modifier

) {
    val apiService = BankingAPIService.callable
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(apiService))
    var country by remember {
        mutableStateOf("")
    }
    var dob by remember { mutableStateOf("") }
    var expandMenu by remember {
        mutableStateOf(false)
    }
    if (expandMenu) {
        CountryBottomSheetMaker(onDismiss = { expandMenu = false })
    }
    val context = LocalContext.current
    val toLogin by viewModel.responseStatus.collectAsState()

    LaunchedEffect(toLogin) {
        if (toLogin) {
            viewModel.resetResponseStatus()
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
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Select your country",
                Modifier
                    .size(40.dp)
                    .padding(top = 16.dp)
                    .align(Alignment.Start)
                    .clickable {}
            )

            Text(
                text = "",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 40.dp)

            )

            Text(
                text = stringResource(id = R.string.app),
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 32.dp),
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "Welcome to Banque Misr!",
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 80.dp),
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Let's Complete your Profile",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 20.dp)
            )




            Text(
                text = "Country",
                modifier = Modifier
                    .padding(end = 300.dp, top = 40.dp),
                fontSize = 16.sp
            )
            //DropdownMenuExample()

            OutlinedTextField(
                value = country,
                onValueChange = { newText -> country = newText },
                label = {
                    Text(
                        text = "Select your country",
                        Modifier.alpha(0.4f),
                        color = colorResource(id = R.color.black)
                    )
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .size(360.dp, 80.dp)
                    .padding(top = 10.dp),

                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow),
                        contentDescription = "Select your country",
                        Modifier
                            .alpha(0.5f)
                            .size(30.dp)
                            .clickable {
                                expandMenu = true
                            }
                    )
                }

            )

            Text(
                text = "Date Of Birth",
                modifier = Modifier
                    .padding(end = 260.dp, top = 40.dp),
                fontSize = 16.sp
            )

            OutlinedTextField(
                value = dob,
                onValueChange = { newText -> dob = newText },
                label = {
                    Text(
                        text = stringResource(id = R.string.date),
                        Modifier.alpha(0.4f),
                        color = colorResource(id = R.color.black)
                    )
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .size(360.dp, 80.dp)
                    .padding(top = 10.dp),

                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = "Date Of Birth",
                        Modifier
                            .alpha(0.5f)
                            .size(30.dp)
                    )
                }

            )


            Button(
                onClick = {
                    val user = UserAuthRegisterRequest(
                        name = name, email = email, password = password,
                        country = country, dateofbirth = dob
                    )
                    viewModel.submitRegistrationData(user)
                },
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(width = 350.dp, height = 60.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Maroon)
            ) {
                Text(
                    text = "Continue",
                    fontSize = 18.sp
                )

            }

            Row {
                Text(
                    text = "Already have an account?",
                    modifier = Modifier
                        .padding(top = 25.dp, start = 30.dp)
                        .alpha(0.6f),
                    color = colorResource(id = R.color.black),
                    fontSize = 15.sp
                )
                Text(
                    text = "Sign In",
                    modifier = Modifier
                        .padding(top = 25.dp, start = 5.dp)
                        .clickable {
                            navController.navigate(AppRoutes.SIGN_IN)
                        },
                    color = Maroon,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline

                )
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryBottomSheetMaker(onDismiss: () -> Unit, modifier: Modifier = Modifier) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        containerColor = White
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Trying Bottom Sheet", modifier = modifier.align(Alignment.TopCenter))
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun SignUp2Preview() {

    //SignUp2(rememberNavController())

}