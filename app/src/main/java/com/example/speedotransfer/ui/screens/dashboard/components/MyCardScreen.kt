package com.example.speedotransfer.ui.screens.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun MyCardScreen(navController: NavController,innerPadding:PaddingValues,modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().
        padding(innerPadding), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "MyCardScreen")

    }
}

@Preview
@Composable
private fun MyCardScreenPreview() {
    //MyCardScreen(rememberNavController())
}