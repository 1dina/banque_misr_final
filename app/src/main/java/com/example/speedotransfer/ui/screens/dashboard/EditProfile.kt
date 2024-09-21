package com.example.speedotransfer.ui.screens.dashboard


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.screens.auth.Texts
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightYellow
import com.example.speedotransfer.ui.theme.Maroon


@Composable
fun EditProfile() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }


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
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Back",
                    Modifier
                        .size(40.dp)
                        .padding(top = 16.dp)
                        .clickable { }
                )

                Text(
                    text = "Edit Profile",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(top = 15.dp, start = 100.dp)

                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy((-35).dp),
                modifier = Modifier.fillMaxHeight()
            ) {

                Texts("Full Name")
                OutlinedTextField(
                    value = name,
                    onValueChange = { newText -> name = newText },
                    label = {
                        Text(
                            text = "Enter Cardholder Name",
                            Modifier.alpha(0.4f),
                            color = colorResource(id = R.color.black)
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = White,
                        unfocusedContainerColor = White, focusedBorderColor = Maroon,
                        focusedTrailingIconColor = Maroon,
                        errorBorderColor = Red,
                        errorContainerColor = White
                    ),
                    modifier = Modifier
                        .size(360.dp, 110.dp)
                        .padding(top = 40.dp)


                )


                Texts("Email")

                OutlinedTextField(
                    value = email,
                    onValueChange = { newText -> email = newText },
                    label = {
                        Text(
                            text = "Enter Cardholder Email",
                            Modifier.alpha(0.4f),
                            color = colorResource(id = R.color.black)
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = White,
                        unfocusedContainerColor = White, focusedBorderColor = Maroon,
                        focusedTrailingIconColor = Maroon,
                        errorBorderColor = Red,
                        errorContainerColor = White
                    ),
                    modifier = Modifier
                        .size(360.dp, 110.dp)
                        .padding(top = 40.dp)
                )


                Text(
                    text = "Country",
                    modifier = Modifier
                        .padding(end = 300.dp, top = 40.dp),
                    fontSize = 16.sp
                )

                Texts("")

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
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = White,
                        unfocusedContainerColor = White, focusedBorderColor = Maroon,
                        focusedTrailingIconColor = Maroon,
                        errorBorderColor = Red,
                        errorContainerColor = White
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
                                .clickable { }
                        )
                    }

                )

                Text(
                    text = "Date Of Birth",
                    modifier = Modifier
                        .padding(end = 260.dp, top = 45.dp),
                    fontSize = 16.sp
                )

                Texts("")


                OutlinedTextField(
                    value = dob,
                    onValueChange = { newText -> dob = newText },
                    label = {
                        Text(
                            text = "DD/MM/YY",
                            Modifier.alpha(0.4f),
                            color = colorResource(id = R.color.black)
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = White,
                        unfocusedContainerColor = White, focusedBorderColor = Maroon,
                        focusedTrailingIconColor = Maroon,
                        errorBorderColor = Red,
                        errorContainerColor = White
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
                        .padding(top = 80.dp)
                        .size(width = 360.dp, height = 60.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.Marron))
                ) {
                    Text(
                        text = "Save",
                        fontSize = 18.sp
                    )

                }


            }
        }
    }

}


@Preview
@Composable
private fun EditProfilePreview() {
    EditProfile()

}