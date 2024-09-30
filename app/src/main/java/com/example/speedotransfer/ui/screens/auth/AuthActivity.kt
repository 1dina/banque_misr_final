package com.example.speedotransfer.ui.screens.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.ui.routes.AuthNavGraph
import com.example.speedotransfer.ui.theme.Maroon
import com.example.speedotransfer.ui.theme.SpeedoTransferTheme

class AuthActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            SpeedoTransferTheme {
                var showDialog by remember { mutableStateOf(false) }

                LaunchedEffect(Unit) {
                    showDialog = intent.getBooleanExtra("showDialog", false)
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    AuthNavGraph(navController)

                    if (showDialog) {
                        SessionExpiredDialog(onDismiss = { showDialog = false })
                    }
                }
            }
        }
    }
}

@Composable
fun SessionExpiredDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Session Expired") },
        text = { Text("Your session has expired. Please log in again.") },
        confirmButton = {
            Button(onClick = onDismiss, colors = ButtonDefaults.buttonColors(containerColor = Maroon)) {
                Text("OK")
            }
        }
    )
}
