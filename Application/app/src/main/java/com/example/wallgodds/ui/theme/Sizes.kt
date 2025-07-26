package com.example.wallgodds.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object AppSize {
    // ElementSizes
    val IconSmall: Dp = 24.dp
    val IconMedium: Dp = 36.dp // e.g. WallGodds, Profile
    val IconLarge: Dp = 48.dp

    val CategoryPreview = 28.dp // Category previews in the Home page

    // CornerRadii
    val LowCornerRadius = 8.dp // e.g. Category previews
    val HighCornerRadius = 24.dp // e.g. Slider, Nav bar
    val WallpaperRoundedCorner = 20.dp // e.g. Wallpaper

    // FontSizes
    val FontSizeSmall = 14.sp
    val FontSizeMedium = 18.sp

    // LazyRow Scrollbar
    val indicatorHeight = 8.dp
    val indicatorWidth = 16.dp
    val trackHeight = 2.dp
    val trackWidth = 120.dp
}