package com.example.speedotransfer.ui.screens.auth

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
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
import com.example.speedotransfer.ui.routes.AppRoutes
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.Maroon

@Composable
fun SignUp1(navController: NavController, modifier: Modifier = Modifier) {

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmpassword by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isPasswordVisible1 by remember { mutableStateOf(false) }
    var isButtonEnabled by remember { mutableStateOf(false) }

    var nameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }
    val scrollState = rememberScrollState()


    isButtonEnabled =
        !(name.isBlank() || email.isBlank() || password.isBlank() || confirmpassword.isBlank())


    fun validateAndShowErrors() {
        when (validateInput(name.trim(), email.trim(), password.trim(), confirmpassword.trim())) {
            1 -> emailError = "Invalid email"
            2 -> nameError = "Full name is invalid (letters only)"
            3 -> passwordError =
                "Password must be at least 6 characters long, with uppercase, lowercase, digit, and special character"

            4 -> confirmPasswordError = "Passwords do not match"
            else -> {
                nameError = null
                emailError = null
                passwordError = null
                confirmPasswordError = null
                navController.navigate("${AppRoutes.LAST_PAGE_SIGN_UP}/$name/$email/$password")
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        White, LightRed
                    )
                )
            )
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "Sign Up", fontSize = 20.sp, modifier = modifier.padding(top = 64.dp)
            )

            Text(
                text = stringResource(id = R.string.app),
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 64.dp),
                fontWeight = FontWeight.Medium
            )

            Column(
                verticalArrangement = Arrangement.spacedBy((-36).dp),
                modifier = modifier.padding(16.dp)
            ) {

                Texts("Full Name")
                OutlinedTextField(value = name,
                    onValueChange = { newText ->
                        name = newText
                        nameError = null
                    }, maxLines = 1,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.name),
                            Modifier.alpha(0.4f),
                            color = colorResource(id = R.color.black)
                        )
                    },
                    isError = nameError != null,
                    shape = RoundedCornerShape(6.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = White,
                        unfocusedContainerColor = White, focusedBorderColor = Maroon,
                        focusedTrailingIconColor = Maroon,
                        errorBorderColor = Red,
                        errorContainerColor = White
                    ),

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),

                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_user),
                            contentDescription = "Name",
                            Modifier
                                .alpha(0.5f)
                                .size(24.dp)
                        )
                    }


                )
                if (nameError != null) {
                    Text(
                        text = nameError ?: "",
                        color = Red,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(top = 32.dp, start = 16.dp)
                    )
                }

                Texts("Email")

                OutlinedTextField(value = email,
                    onValueChange = { newText ->
                        email = newText
                        emailError = null
                    }, maxLines = 1,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.email),
                            Modifier.alpha(0.4f),
                            color = colorResource(id = R.color.black)
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    isError = emailError != null,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = White,
                        unfocusedContainerColor = White, focusedBorderColor = Maroon,
                        focusedTrailingIconColor = Maroon,
                        errorBorderColor = Red,
                        errorContainerColor = White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = "Email",
                            Modifier
                                .alpha(0.5f)
                                .size(24.dp)
                        )
                    })
                if (emailError != null) {
                    Text(
                        text = emailError ?: "",
                        color = Red,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(top = 32.dp, start = 16.dp)
                    )
                }

                Texts("Password")

                OutlinedTextField(value = password,
                    onValueChange = { newText ->
                        password = newText
                        passwordError = null
                    }, maxLines = 1,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.password),
                            Modifier.alpha(0.4f),
                            color = colorResource(id = R.color.black),
                            textAlign = TextAlign.Start
                        )
                    },
                    isError = passwordError != null,
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = White,
                        unfocusedContainerColor = White, focusedBorderColor = Maroon,
                        focusedTrailingIconColor = Maroon,
                        errorBorderColor = Red,
                        errorContainerColor = White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        Icon(painter = if (!isPasswordVisible) painterResource(id = R.drawable.ic_visibility)
                        else painterResource(id = R.drawable.ic_eye),
                            contentDescription = "Password isn't visible",
                            Modifier
                                .alpha(0.5f)
                                .size(24.dp)
                                .clickable {
                                    isPasswordVisible = !isPasswordVisible
                                })
                    }


                )
                if (passwordError != null) {
                    Text(
                        text = passwordError ?: "",
                        color = Red,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(top = 32.dp, start = 16.dp)
                    )
                }

                Texts("Confirm password")

                OutlinedTextField(value = confirmpassword,
                    onValueChange = { newText ->
                        confirmpassword = newText
                        confirmPasswordError = null
                    }, maxLines = 1,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.password),
                            Modifier.alpha(0.4f),
                            color = colorResource(id = R.color.black),
                            textAlign = TextAlign.Start
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    isError = confirmPasswordError != null,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = White,
                        unfocusedContainerColor = White, focusedBorderColor = Maroon,
                        focusedTrailingIconColor = Maroon,
                        errorBorderColor = Red,
                        errorContainerColor = White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    visualTransformation = if (isPasswordVisible1) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        Icon(painter = if (!isPasswordVisible1) painterResource(id = R.drawable.ic_visibility)
                        else painterResource(id = R.drawable.ic_eye),
                            contentDescription = "Password",
                            Modifier
                                .alpha(0.5f)
                                .size(24.dp)
                                .clickable {
                                    isPasswordVisible1 = !isPasswordVisible1

                                })
                    }


                )
                if (confirmPasswordError != null) {
                    Text(
                        text = confirmPasswordError ?: "",
                        color = Red,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(top = 32.dp, start = 16.dp)
                    )
                }
                Button(
                    onClick = { validateAndShowErrors() },
                    modifier = Modifier
                        .padding(top = 64.dp)
                        .fillMaxWidth(),
                    enabled = isButtonEnabled,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Maroon)
                ) {
                    Text(
                        text = "Sign up", fontSize = 16.sp, modifier = modifier.padding(10.dp)
                    )

                }

            }


            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Already have an account?",
                    modifier = Modifier.alpha(0.6f),
                    color = colorResource(id = R.color.black),
                    fontSize = 16.sp
                )
                Text(
                    text = "Sign In",
                    modifier = Modifier
                        .padding(start = 3.dp)
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

    }
}


fun validateInput(
    fullName: String,
    email: String,
    password: String,
    confirmPassword: String,
): Int {
    return when {
        !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
            1
        }

        !fullName.matches(Regex("^[A-Za-z\\s]+$")) -> {
            2
        }

        password.isEmpty() || password.length < 6 -> {
            3
        }

        !password.matches(Regex(".*[A-Z].*")) -> {
            3
        }

        !password.matches(Regex(".*[a-z].*")) -> {
            3
        }

        !password.matches(Regex(".*\\d.*")) -> {
            3
        }

        !password.matches(Regex(".*[@\$!%*?&].*")) -> {
            3
        }

        password != confirmPassword -> {
            4
        }


        else -> 5
    }
}


@Composable
fun Texts(text: String) {
    Text(
        text = text, modifier = Modifier.padding(top = 48.dp), fontSize = 16.sp
    )

}


@Preview(showSystemUi = true)
@Composable
private fun SignUp1Preview() {
    SignUp1(rememberNavController())

}