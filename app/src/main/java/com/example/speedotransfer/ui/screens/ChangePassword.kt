package com.example.speedotransfer.ui.screens


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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightYellow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePassword(modifier : Modifier = Modifier) {

    var confirmpassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isPasswordVisible1 by remember { mutableStateOf(false) }



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
                    text = "Change Password",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(top = 15.dp, start = 80.dp)

                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(-25.dp),
                modifier = Modifier.fillMaxHeight()
            ) {


                Texts("Current password")

                OutlinedTextField(
                    value = confirmpassword,
                    onValueChange = { newText -> confirmpassword = newText },
                    label = {
                        Text(
                            text = "Enter your password",
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

                Texts("New password")

                OutlinedTextField(
                    value = confirmpassword,
                    onValueChange = { newText -> confirmpassword = newText },
                    label = {
                        Text(
                            text = "Enter your password",
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
            }
        }
    }


}

@Preview
@Composable
private fun ChangePasswordPreview() {

    ChangePassword()

}