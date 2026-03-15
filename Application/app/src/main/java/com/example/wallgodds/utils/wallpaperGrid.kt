package com.example.wallgodds.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.wallgodds.ui.theme.AppPadding

fun LazyGridScope.wallpaperGrid(
    wallpapers: List<Any>,
    horizontalPadding: Dp = AppPadding.MainContentPadding,
    onClick: () -> Unit = {},
    navController: NavController
) {

    itemsIndexed(wallpapers) { index, wallpaper ->
        val startPadding = if (index % 2 == 0) horizontalPadding else 6.dp
        val endPadding = if (index % 2 == 0) 6.dp else horizontalPadding

        Box(
            modifier = Modifier
                .padding(start = startPadding, end = endPadding)
                .height(320.dp)
                .clip(RoundedCornerShape(24.dp))
        ) {
            AsyncImage(
                model = wallpaper,
                contentDescription = "Wallpaper image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        navController.navigate("wallpaper_preview/$wallpaper")
                    }

            )
        }
    }
}