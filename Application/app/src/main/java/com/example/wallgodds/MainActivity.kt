package com.example.wallgodds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.remember
import com.example.wallgodds.navigation.CustomNavigationBar
import com.example.wallgodds.navigation.Routes
import com.example.wallgodds.navigation.listOfNavItems
import com.example.wallgodds.screens.FavoritesPage
import com.example.wallgodds.screens.HomePage
import com.example.wallgodds.screens.UploadImagePage
import com.example.wallgodds.screens.ProfilePage
import com.example.wallgodds.screens.SignUpScreen
import com.example.wallgodds.screens.UploadPage
import com.example.wallgodds.screens.WallpaperPreviewPage
import com.example.wallgodds.ui.theme.WallGoddsTheme
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource
import androidx.navigation.NavType
import androidx.navigation.navArgument


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
            android.graphics.Color.TRANSPARENT,
            android.graphics.Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT)
        )
        setContent {
            WallGoddsTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val hazeState = remember { HazeState() }

                Scaffold(
                    floatingActionButton = {
                        if (navBackStackEntry?.destination?.route in listOfNavItems.map { it.route } || navBackStackEntry?.destination?.route == Routes.upload_image_page) {
                            CustomNavigationBar(
                                hazeState = hazeState,
                                currentDestination = currentDestination,
                                items = listOfNavItems,
                                onItemClick = { item ->
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = item.route != Routes.upload_page
                                    }
                                }
                            )
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                ) { innerPadding ->


                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .hazeSource(state = hazeState),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.background),
                            contentDescription = "Background Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.matchParentSize())

                        NavHost(
                            navController = navController,
                            startDestination = Routes.home_page,
                            modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
                        ) {
                            composable(Routes.favorites_page) {
                                FavoritesPage(navController)
                            }
                            composable(Routes.home_page) {
                                HomePage(navController)
                            }
                            composable(Routes.upload_page) {
                                UploadPage(navController)
                            }
                            composable(Routes.upload_image_page) {
                                UploadImagePage(navController)
                            }
                            composable(Routes.profile_page) {
                                ProfilePage(navController)
                            }
                            composable(Routes.signup_page) {
                                SignUpScreen()
                            }
                            composable(
                                route = "${Routes.wallpaper_preview}/{wallpaper}",
                                arguments = listOf(navArgument("wallpaper") { type = NavType.IntType })
                            ) { backStackEntry ->

                                val wallpaper =
                                    backStackEntry.arguments?.getInt("wallpaper") ?: R.drawable.wall1

                                WallpaperPreviewPage(
                                    navController = navController,
                                    wallpaper = wallpaper
                                )
                        }
                    }

                }
            }

        }
    }
}

