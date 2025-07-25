package com.example.wallgodds.navigation

import com.example.wallgodds.R

data class NavItems (
    val label: String,
    val icon: Int,
    val route: String
)

val listOfNavItems: List<NavItems> = listOf(
    NavItems(
        label = "Home",
        icon = R.drawable.home_icon,
        route = Routes.home_page
    ),
    NavItems(
        label = "Favorites",
        icon = R.drawable.favorites_icon,
        route = Routes.favorites_page
    ),
    NavItems(
        label = "Upload",
        icon = R.drawable.upload_icon,
        route = Routes.upload_page
    )
)