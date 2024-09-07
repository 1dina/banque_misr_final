package com.example.speedotransfer.ui.screens

import android.widget.Toast
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
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.LightRed
import java.text.SimpleDateFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp2(modifier: Modifier = Modifier) {

    var dob by remember { mutableStateOf("") }
    var isDatePickerShown by remember { mutableStateOf(false) }

    var dateMillis by remember { mutableLongStateOf(0L) }

    if(isDatePickerShown)
        DatePickerChooser(onConfirm = {
            val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
            dateMillis = it.selectedDateMillis!!
            dob = dateFormatter.format(dateMillis)
            isDatePickerShown = false
        }) {
            isDatePickerShown = false

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
                painter = painterResource(id = R.drawable.ic_arrow_back),
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
                modifier = Modifier.padding(top = 42.dp)

            )

            Text(
                text = stringResource(id = R.string.app),
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 32.dp),
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "Welcome to Banque Misr!",
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 80.dp),
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Let's Complete your Profile",
                fontSize = 16.sp,
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
                value = dob,
                onValueChange = { newText -> dob = newText },
                placeholder = {
                    Text(
                        text = "Select your country",
                        Modifier.alpha(0.4f),
                        color = colorResource(id = R.color.black)
                    )
                },
                readOnly = true,
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
                            .clickable {  }
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
                            .clickable {
                                isDatePickerShown = true
                            }
                    )
                }

            )


            Button(
                onClick = { },
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(width = 350.dp, height = 60.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.Marron))
            ) {
                Text(
                    text = "Continue",
                    fontSize = 16.sp
                )

            }

            Row {
                Text(
                    text = "Already have an account?",
                    modifier = Modifier
                        .padding(top = 25.dp, start = 30.dp)
                        .alpha(0.6f),
                    color = colorResource(id = R.color.black),
                    fontSize = 16.sp
                )
                Text(
                    text = "Sign In",
                    modifier = Modifier.padding(top = 25.dp, start = 5.dp),
                    color = colorResource(id = R.color.Marron),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline

                )
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerChooser(onConfirm: (DatePickerState) -> Unit, onDismiss: () -> Unit) {
    val todayInMillis = System.currentTimeMillis()
    val datePickerState = rememberDatePickerState()
    val context = LocalContext.current

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = {
                val selectedDate = datePickerState.selectedDateMillis ?: todayInMillis
                if (selectedDate <= todayInMillis) {
                    onConfirm(datePickerState) // Only confirm if the date is valid
                } else {
                    Toast.makeText(context,"Please Choose date in the past",Toast.LENGTH_SHORT).show()
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
        // DatePicker widget with the state
        DatePicker(state = datePickerState)
    }
}


@Preview(showSystemUi = true)
@Composable
private fun SignUp2Preview() {

    SignUp2()

}