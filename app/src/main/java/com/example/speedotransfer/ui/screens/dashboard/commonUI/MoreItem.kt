package com.example.speedotransfer.ui.screens.dashboard.commonUI

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.Grey

@Composable
fun MoreItem(
    @DrawableRes leadingIcon: Int,
    itemText: String,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
    ) {
        Row(
            modifier = modifier
                .padding(8.dp)
                .fillMaxSize()
                .clickable { onItemClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = leadingIcon),
                contentDescription = itemText,
                tint = Grey,
                modifier = modifier.size(24.dp)
            )
            Text(
                text = itemText,
                fontSize = 16.sp,
                color = Grey,
                fontWeight = FontWeight.Medium,
                modifier = modifier.padding(start = 8.dp)
            )
            Spacer(modifier = modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_drop_down),
                contentDescription = "go to $itemText",
                tint = Grey,
                modifier = modifier.size(24.dp)
            )
        }
        HorizontalDivider(
            modifier = modifier
                .height(0.5.dp)
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )
    }


}

