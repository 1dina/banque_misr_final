package com.example.speedotransfer.ui.screens.dashboard.commonUI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R

@Composable
fun HeaderUI(
    headerTitle: String,
    onClickBackButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "Back Button",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable { onClickBackButton() }
                .padding(8.dp)
        )

        Text(
            text = headerTitle,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium
        )
    }
}
