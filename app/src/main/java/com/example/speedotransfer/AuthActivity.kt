package com.example.speedotransfer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.speedotransfer.routes.AuthNavGraph
import com.example.speedotransfer.ui.theme.SpeedoTransferTheme

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpeedoTransferTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    AuthNavGraph()
                }
            }
        }
    }
}
