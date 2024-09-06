package com.example.speedotransfer.ui.screens

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp1(modifier: Modifier = Modifier) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isPasswordVisible1 by remember { mutableStateOf(false) }



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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {


            Text(
                text = "Sign Up",
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

                Texts("Full Name")
                OutlinedTextField(
                    value = name,
                    onValueChange = { newText -> name = newText },
                    label = {
                        Text(
                            text = stringResource(id = R.string.name),
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
                            painter = painterResource(id = R.drawable.ic_person),
                            contentDescription = "Name",
                            Modifier
                                .alpha(0.5f)
                                .size(30.dp)
                        )
                    }


                )


                Texts("Email")

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
                            contentDescription = "Email",
                            Modifier
                                .alpha(0.5f)
                                .size(30.dp)
                        )
                    }
                )

                Texts("Password")

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

                Texts("Confirm password")

                OutlinedTextField(
                    value = confirmpassword,
                    onValueChange = { newText -> confirmpassword = newText },
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
            Button(
                onClick = { },
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(width = 350.dp, height = 60.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.Marron))
            ) {
                Text(
                    text = "Sign up",
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
                        .clickable {  },
                    color = colorResource(id = R.color.Marron),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline

                )
            }
        }

    }
}


fun validateInput(
    fullName: String,
    email: String,
    password: String,
    confirmPassword: String,
): Boolean {
    return when {
        email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
            .matches() -> {
            false
        }

        fullName.isEmpty() || !fullName.matches(Regex("^[A-Za-z]+$")) -> {
            false
        }

        password.isEmpty() || password.length < 6 || !password.any { it.isDigit() ||
                !password.matches(Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*[@\$!%*?&])\$\n"))} -> {
            false
        }

//        confirmPassword.isEmpty() || confirmPassword.length < 6 || !confirmPassword.any { it.isDigit() } -> {
//            false
//        }

        password!=confirmPassword -> {
            false
        }





        else -> true
    }
}


@Composable
fun Texts(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(top = 50.dp),
        fontSize = 16.sp
    )

}


@Preview(showSystemUi = true)
@Composable
private fun SignUp1Preview() {

    SignUp1()

}