package com.example.wallgodds.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.AppSize
import com.example.wallgodds.utils.TopAppBar

@Composable
fun FavoritesPageScreen(navController: NavController) {
    val favoriteWallpapers = List(30) { R.drawable.sample_wallpaper } // Static list for now

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .padding(horizontal = AppPadding.MainContentPadding)
        ) {
            TopAppBar(navController = navController)
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(AppPadding.Small),
                horizontalArrangement = Arrangement.spacedBy(AppPadding.Small),
                modifier = Modifier.fillMaxSize()
                    .padding(bottom = 100.dp)
            ) {
                items(favoriteWallpapers) { wallpaperResId ->
                    Image(
                        painter = painterResource(id = wallpaperResId),
                        contentDescription = "Favorite Wallpaper",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .aspectRatio(9f / 16f)
                            .clip(RoundedCornerShape(AppSize.WallpaperRoundedCorner))
                    )
                }
            }
        }
    }
}