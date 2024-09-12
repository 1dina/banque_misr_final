package com.example.speedotransfer.ui.screens.dashboard.commonUI


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R

@Composable
fun ProfileScreen( text1 : String , text2: String , image : Painter , imageText: String , onItemClick:()->Unit) {

    Column(
        modifier = Modifier
            .padding(start = 15.dp, top = 10.dp)
    ) {
        Card(
            modifier = Modifier
                .size(48.dp, 50.dp)
                .clickable { onItemClick() },
        ) {
            Image(
                painter = image,
                contentDescription = imageText,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
    Column(
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp)
    ) {
        Text(
            text = text1,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = text2,
            fontSize = 14.sp,
            modifier = Modifier.alpha(0.8f)
        )



    }
    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_forward),
            contentDescription = "See Details",
            Modifier
                .size(32.dp)
                .padding(top = 16.dp)
                .alpha(0.5f)
                .clickable {  }
        )
    }
}


