package com.example.speedotransfer.ui.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.data.models.UserAuthRegisterRequest
import com.example.speedotransfer.data.source.BankingAPIService
import com.example.speedotransfer.routes.AppRoutes
import com.example.speedotransfer.ui.theme.LightGrey
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.Maroon
import com.example.speedotransfer.ui.viewmodels.AuthViewModel
import com.example.speedotransfer.ui.viewmodels.AuthViewModelFactory
import java.text.SimpleDateFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp2(
    navController: NavController,
    name: String,
    email: String,
    password: String,
    modifier: Modifier = Modifier
) {
    var isButtonEnabled by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val apiService = BankingAPIService.callable
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(apiService, context = context))
    var country by rememberSaveable {
        mutableStateOf("")
    }
    var dob by rememberSaveable { mutableStateOf("") }
    var dobInternal by rememberSaveable {
        mutableStateOf("")
    }
    var isDatePickerShown by remember { mutableStateOf(false) }
    var isLoading by remember {
        mutableStateOf(false)
    }

    var expandMenu by remember {
        mutableStateOf(false)
    }
    if (expandMenu) {
        CountryBottomSheetMaker(onDismiss = { expandMenu = false })
    }
    isButtonEnabled = if (!(country.isBlank() || dob.isBlank())) {
        true
    }
    else {
        false
    }

        if (isDatePickerShown) {
            DatePickerChooser(
                onConfirm = { displayDate, internalDate ->
                    dob = displayDate
                    dobInternal = internalDate
                    isDatePickerShown = false
                },
                onDismiss = {
                    isDatePickerShown = false
                }
            )
        }

    val toLogin by viewModel.responseStatus.collectAsState()

    LaunchedEffect(toLogin) {
        if (toLogin == true) {
            isLoading = false
            viewModel.resetResponseStatus()
            navController.navigate(AppRoutes.SIGN_IN)
        } else {
            isLoading = false
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
                        White,
                        LightRed
                    )
                )
            )
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {


            Icon(
                painter = painterResource(id = R.drawable.ic_back), contentDescription = "Go back",
                modifier = modifier
                    .size(24.dp)
                    .padding(top = 8.dp)
                    .clickable {
                        navController.navigate(AppRoutes.FIRST_PAGE_SIGN_UP)
                    }
            )

            Text(
                text = stringResource(id = R.string.app),
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "Welcome to Banque Misr!",
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(top = 72.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Let's Complete your Profile",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )




            Text(
                text = "Country",
                modifier = Modifier
                    .padding(top = 40.dp),
                fontSize = 16.sp
            )

            OutlinedTextField(
                value = country,
                onValueChange = { newText -> country = newText },maxLines = 1,
                placeholder = {
                    Text(
                        text = "Select your country",
                        Modifier.alpha(0.4f),
                        color = colorResource(id = R.color.black)
                    )
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White, focusedBorderColor = Maroon
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),

                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow),
                        contentDescription = "Select your country",
                        Modifier
                            .alpha(0.5f)
                            .size(24.dp)
                            .clickable {
                                expandMenu = true
                            }
                    )
                }

            )

            Text(
                text = "Date Of Birth",
                modifier = modifier
                    .padding(top = 16.dp),
                fontSize = 16.sp
            )

            OutlinedTextField(
                value = dob,
                readOnly = true,
                onValueChange = { newText -> dob = newText },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.date),
                        Modifier.alpha(0.4f),
                        color = colorResource(id = R.color.black)
                    )
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = White, focusedBorderColor = Maroon
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),

                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = "Date Of Birth",
                        Modifier
                            .alpha(0.5f)
                            .size(24.dp)
                            .clickable {
                                isDatePickerShown = true
                            }
                    )
                }

            )


            Button(
                onClick = {
                    isLoading = true
                    val user = UserAuthRegisterRequest(
                        name = name, email = email, password = password,
                        country = country, dateofbirth = dobInternal
                    )
                    viewModel.submitRegistrationData(user)
                },
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(),
                enabled = isButtonEnabled,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Maroon)
            ) {
                Text(
                    text = "Continue",
                    fontSize = 16.sp,
                    modifier = modifier.padding(10.dp)
                )

            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(
                    text = "Already have an account?",
                    modifier = Modifier
                        .alpha(0.6f),
                    color = colorResource(id = R.color.black),
                    fontSize = 16.sp
                )
                Text(
                    text = "Sign In",
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .clickable {
                            navController.navigate(AppRoutes.SIGN_IN)
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

@Composable
fun IndeterminateCircularIndicator() {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = Maroon,
        trackColor = LightGrey
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerChooser(onConfirm: (String, String) -> Unit, onDismiss: () -> Unit) {
    val todayInMillis = System.currentTimeMillis()
    val datePickerState = rememberDatePickerState()
    val context = LocalContext.current
    val dateFormatterDisplay = SimpleDateFormat("dd/MM/yy", Locale.US)
    val dateFormatterInternal = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = {
                val selectedDate = datePickerState.selectedDateMillis ?: todayInMillis
                if (selectedDate <= todayInMillis) {
                    val displayDate = dateFormatterDisplay.format(selectedDate)
                    val internalDate = dateFormatterInternal.format(selectedDate)
                    onConfirm(displayDate, internalDate) // Only confirm if the date is valid
                } else {
                    Toast.makeText(context, "Please Choose date in the past", Toast.LENGTH_SHORT)
                        .show()
                }
            }) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = "Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
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
    SignUp2(rememberNavController(), "Dina", "dindfjdjf", "dfjdj")

}