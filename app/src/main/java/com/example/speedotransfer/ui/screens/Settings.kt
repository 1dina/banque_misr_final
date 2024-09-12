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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightYellow


@Composable
fun Settings(modifier : Modifier = Modifier) {

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
                text = "Setting",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(top = 15.dp, start = 130.dp)

            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 120.dp)
                .fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxHeight(0.1f)
                    .padding(top = 5.dp)
            ) {
                ProfileScreen(
                    text1 = "Change password",
                    text2 = "Change password",
                    image = painterResource(
                        id = R.drawable.changepassword
                    ),
                    imageText = "Lock",
                    onItemClick = {}

                )
            }



            Row(
                modifier = Modifier
                    .fillMaxHeight(0.13f)
                    .padding(top = 5.dp)
            ) {
                ProfileScreen(
                    text1 = "Edit Profile", text2 = "Change your information", image = painterResource(
                        id = R.drawable.editprofile
                    ), imageText = "Edit Profile Details",
                    onItemClick = {}

                )
            }


            Divider(
                modifier = Modifier
                    .padding(vertical = 0.dp)
                    .padding(start = 15.dp)
                    .width(370.dp)
                    .alpha(0.2f),
                color = Color.Gray,
                thickness = 2.dp
            )



        }

    }

}

@Preview
@Composable
private fun SettingsPreview() {

    Settings()

}