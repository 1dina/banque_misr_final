package com.example.speedotransfer.ui.screens.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.data.source.remote.retrofit.RetrofitInstance
import com.example.speedotransfer.ui.routes.AppRoutes
import com.example.speedotransfer.ui.routes.DashboardNavGraph
import com.example.speedotransfer.ui.theme.Grey
import com.example.speedotransfer.ui.theme.Marron
import com.example.speedotransfer.ui.theme.SpeedoTransferTheme
import com.example.speedotransfer.ui.viewmodels.home.HomeViewModel
import com.example.speedotransfer.ui.viewmodels.home.HomeViewModelFactory

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val apiService = RetrofitInstance.callable
            val viewModel: HomeViewModel =
                viewModel(factory = HomeViewModelFactory(apiService, context = this))
            val screensWithoutBottomBar = setOf(
                AppRoutes.SETTINGS,
                AppRoutes.PROFILE_INFO,
                AppRoutes.UPDATE_PASSWORD
            )

            SpeedoTransferTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        val currentRoute =
                            navController.currentBackStackEntryAsState().value?.destination?.route
                        if (currentRoute !in screensWithoutBottomBar) {
                            BottomAppBar(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp)
                                    .shadow(8.dp, RoundedCornerShape(24.dp)),
                                containerColor = Color.White,
                                contentPadding = PaddingValues(16.dp),
                            ) {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                BottomNavBarScreens.getBottomNavBarItems().forEach { screen ->
                                    NavigationBarItem(
                                        icon = {
                                            Icon(
                                                painter = painterResource(id = screen.itemImage),
                                                contentDescription = stringResource(id = screen.resourceId),
                                                modifier = Modifier.size(20.dp)
                                            )
                                        },
                                        label = {
                                            Text(
                                                stringResource(screen.resourceId),
                                                style = TextStyle(
                                                    fontSize = 10.sp,
                                                    textAlign = TextAlign.Center
                                                )
                                            )
                                        },
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                popUpTo(AppRoutes.HOME) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        colors = NavigationBarItemDefaults.colors(
                                            selectedTextColor = Marron,
                                            unselectedTextColor = Grey,
                                            selectedIconColor = Marron,
                                            unselectedIconColor = Grey,
                                            indicatorColor = Color.White
                                        )
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    DashboardNavGraph(navController, innerPadding = innerPadding, viewModel)
                }
            }
        }
    }

}
