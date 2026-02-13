package com.example.wallgodds.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.poppinsFontFamily
import com.example.wallgodds.utils.WallpaperGrid
import com.example.wallgodds.wallpapers.wallpapers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun HomePage(navController: NavController) {
    Column {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item(span = { GridItemSpan(2) }) {
                    TopSection()
                }

                item(span = { GridItemSpan(2) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .height(100.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = R.drawable.cardbackground,
                            contentDescription = "Background",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Text(
                            text = "Need laptop or desktop wallpapers?\nVisit WallGodds website",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                            fontFamily = poppinsFontFamily
                        )
                    }
                }

                WallpaperGrid(wallpapers = wallpapers)
            }
        }
    }
}

@Composable
fun TopSection() {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(54.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White.copy(alpha = 0.4f))
        ) {
            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontFamily = poppinsFontFamily
                ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            if (searchText.isEmpty()) {
                                Text(
                                    text = "Search",
                                    color = Color(0xFF9E9E9E),
                                    fontSize = 16.sp,
                                    fontFamily = poppinsFontFamily
                                )
                            }
                            innerTextField()
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search_figma),
                            contentDescription = "Search",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        val categories = listOf(
            "Abstract" to R.drawable.wall1,
            "Nature" to R.drawable.wall2,
            "Anime" to R.drawable.wall3,
            "Art" to R.drawable.wall4,
            "Music" to R.drawable.wall5,
            "Cars" to R.drawable.wall6
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
                .padding(start = 24.dp, top = 6.dp, bottom = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            categories.forEach { (name, imageRes) ->
                Box(
                    modifier = Modifier
                        .size(width = 76.dp, height = 32.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = imageRes,
                        contentDescription = name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.matchParentSize()
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(Color.Black.copy(alpha = 0.35f))
                    )
                    Text(
                        text = name,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val trackWidth = 120.dp
            val thumbWidth = 24.dp
            val density = LocalDensity.current
            
            val trackWidthPx = with(density) { trackWidth.toPx() }
            val thumbWidthPx = with(density) { thumbWidth.toPx() }
            val dragRange = trackWidthPx - thumbWidthPx
            
            Box(
                modifier = Modifier
                    .width(trackWidth)
                    .height(2.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFF808080))
            )

            Box(
                modifier = Modifier
                    .width(trackWidth)
                    .height(8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                 Box(
                    modifier = Modifier
                        .offset {
                            val maxScroll = scrollState.maxValue.toFloat()
                            val scrollValue = scrollState.value.toFloat()
                            
                            val offset = if (maxScroll > 0) {
                                (scrollValue / maxScroll) * dragRange
                            } else {
                                0f
                            }
                            
                            IntOffset(x = offset.roundToInt(), y = 0)
                        }
                        .width(thumbWidth)
                        .height(8.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color.Black)
                        .draggable(
                            orientation = Orientation.Horizontal,
                            state = rememberDraggableState { delta ->
                                val maxScroll = scrollState.maxValue.toFloat()
                                if (maxScroll > 0) {
                                    val scale = maxScroll / dragRange
                                    val scrollDelta = delta * scale
                                    coroutineScope.launch {
                                        scrollState.scrollBy(scrollDelta)
                                    }
                                }
                            }
                        )
                )
            }
        }
    }
}


