package com.example.speedotransfer.ui.screens.dashboard.commonUI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.Grey

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
                .size(24.dp)

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

@Composable
fun HeaderUIWithCancel(
    headerTitle: String,
    headerOption: String,
    onClickBackButton: () -> Unit,
    onOptionClick:()->Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        // Back Button
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "Back Button",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable { onClickBackButton() }
                .size(24.dp)
        )

        // Title Text (centered)
        Text(
            text = headerTitle,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.Center)
        )

        // Option Button
        TextButton(
            onClick = { onOptionClick() },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text(
                text = headerOption,
                fontSize = 14.sp,
                color = Grey
            )
        }
    }
}


