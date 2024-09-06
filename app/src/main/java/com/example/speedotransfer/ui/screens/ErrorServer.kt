package com.example.speedotransfer.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.LightRed
import com.example.speedotransfer.ui.theme.LightYellow

@Composable
fun ErrorServer(modifier : Modifier = Modifier) {

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
    ){
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(painter = painterResource(id = R.drawable.error),
                contentDescription ="Server Error",
                modifier = Modifier
                    .size(300.dp))

            Text(
                text = "Server error...",
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 32.dp),
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "It seems like we’re haveing some diffculities , please don’t leave your aspirations, we are sending for help",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center
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
                    text = "Call Us",
                    fontSize = 18.sp
                )

            }

            Button(
                onClick = { },
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(width = 350.dp, height = 60.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                border = BorderStroke(1.dp , colorResource(id = R.color.Marron))
            ) {
                Text(
                    text = "Message Us",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.Marron)
                )

            }

        }
    }


}

@Preview
@Composable
private fun ErrorServerPreview() {

    ErrorServer()
}
