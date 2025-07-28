package com.example.wallgodds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.wallgodds.navigation.CustomNavigationBar
import com.example.wallgodds.navigation.Routes
import com.example.wallgodds.navigation.listOfNavItems
import com.example.wallgodds.screens.FavoritesPageScreen
import com.example.wallgodds.screens.HomePage
import com.example.wallgodds.screens.ProfilePageScreen
import com.example.wallgodds.screens.UploadPage
import com.example.wallgodds.ui.theme.WallGoddsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WallGoddsTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                Scaffold(
                    floatingActionButton = {
                        if (navBackStackEntry?.destination?.route in listOfNavItems.map { it.route }) {
                            Box(
                                modifier = Modifier.offset(y = 16.dp)
                            ) {
                                CustomNavigationBar(
                                    currentDestination = currentDestination,
                                    items = listOfNavItems,
                                    onItemClick = { item ->
                                        navController.navigate(item.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.home_page,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Routes.favorites_page) {
                            FavoritesPageScreen(navController)
                        }
                        composable(Routes.home_page) {
                            HomePage(navController)
                        }
                        composable(Routes.upload_page) {
                            UploadPage(navController)
                        }
                        composable(Routes.profile_page) {
                            ProfilePageScreen(
                                onBackPressed = { navController.popBackStack() },
                                onDarkModeClicked = {},
                                onFeedbackClicked = {},
                                onLogoutClicked = {},
                                onDeleteAccountClicked = {}
                            )
                        }
                    }
                }
            }
        }
    }
}