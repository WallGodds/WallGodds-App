package com.example.wallgodds.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.AppSize
import com.example.wallgodds.utils.LazyRowScrollbar
import com.example.wallgodds.utils.RandomWallpaperGrid
import com.example.wallgodds.utils.TopAppBar

@Composable
fun HomePage(navController: NavController) {

    val wallpapers = List(50) {
        R.drawable.sample_wallpaper
    }

    val listState = rememberLazyListState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // *** Use this as the background for your page ***
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                modifier = Modifier.padding(horizontal = AppPadding.MainContentPadding),
                navController = navController
            )
            LazyRow(
                state = listState,
                contentPadding = PaddingValues(horizontal = AppPadding.MainContentPadding),
                horizontalArrangement = Arrangement.spacedBy(AppPadding.PaddingBetweenCategories)
            ) {
                listOf<String>(
                    "Abstract",
                    "Nature",
                    "Anime",
                    "Art",
                    "Movies",
                    "Vehicles",
                    "Sports",
                    "Games",
                    "Travel",
                    "Spiritual",
                    "Music",
                    "AIGen",
                ).forEach {
                    item {
                        Box(
                            modifier = Modifier
                                .height(AppSize.CategoryPreview)
                                .aspectRatio(3f / 1f)
                                .clip(RoundedCornerShape(AppSize.LowCornerRadius))
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.category_abstract),
                                contentDescription = "Book Cover",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            Text(
                                text = it,
                                color = Color.White,
                                fontSize = AppSize.FontSizeSmall,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = 0.5f)),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
            LazyRowScrollbar(
                listState = listState,
                indicatorColor = Color.Black,
                indicatorHeight = AppSize.indicatorHeight,
                indicatorWidth = AppSize.indicatorWidth,
                trackColor = Color.Gray,
                trackHeight = AppSize.trackHeight,
                trackWidth = AppSize.trackWidth,
                cornerRadius = AppSize.HighCornerRadius,
                verticalPadding = AppPadding.Medium
            )
            RandomWallpaperGrid(wallpapers = wallpapers)
        }
    }
}