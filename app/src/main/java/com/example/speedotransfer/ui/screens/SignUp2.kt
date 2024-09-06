package com.example.speedotransfer.ui.screens

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp2(modifier: Modifier = Modifier) {

    var dob by remember { mutableStateOf("") }



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
                value = dob,
                onValueChange = { newText -> dob = newText },
                label = {
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
                onClick = { },
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(width = 350.dp, height = 60.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.Marron))
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
                    modifier = Modifier.padding(top = 25.dp, start = 5.dp),
                    color = colorResource(id = R.color.Marron),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline

                )
            }
        }

    }

}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DropdownMenuExample() {
//    var expanded by remember { mutableStateOf(false) }
//    var selectedOption by remember { mutableStateOf("Select a country") }
//
//    val options = listOf("United States", "Canada", "Mexico", "Brazil", "Argentina")
//
//    ExposedDropdownMenuBox(
//        expanded = expanded,
//        onExpandedChange = { expanded = !expanded }
//    ) {
//        // Outlined TextField for the dropdown
//        TextField(
//            readOnly = true,
//            value = selectedOption,
//            onValueChange = { },
//            trailingIcon = {
//                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
//            },
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                containerColor = Color.White // Background color of TextField
//
//            ),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//                .menuAnchor(),
//            shape = RoundedCornerShape(10.dp)
//
//        )
//
//        // Dropdown menu items
//        ExposedDropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            options.forEach { option ->
//
//                DropdownMenuItem(
//                    onClick = {
//                        selectedOption = option
//                        expanded = false
//                    },
//                    text = {
//                        Text(
//                            text = option,
//                            fontSize = 16.sp,
//                            color = Color.Black
//                        )
//                 sign   }
//                )
//            }
//        }
//    }
//}


@Preview(showSystemUi = true)
@Composable
private fun SignUp2Preview() {

    SignUp2()

}