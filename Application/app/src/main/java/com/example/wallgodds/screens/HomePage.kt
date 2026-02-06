package com.example.wallgodds.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.wallgodds.R
import com.example.wallgodds.utils.WallpaperGrid
import com.example.wallgodds.wallpapers.wallpapers

private val guedfont = FontFamily(
    Font(R.font.gued)
)

@Composable
fun HomePage(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        
        // 1. Search Bar
        SearchBar()
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // 2. Category Scroll Row
        CategoryRow()
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // 3. Scroll / Selection Indicator
        ScrollIndicator()
        
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp), // Reduced top padding
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item(span = { GridItemSpan(2) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
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
                            color = Color(0xB2FFFFFF),
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                            fontFamily = guedfont
                        )
                    }
                }

                WallpaperGrid(wallpapers = wallpapers)
            }
        }
    }
}

@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Search", color = Color.Gray, fontSize = 16.sp) }, // Placeholder exactly as expected
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(56.dp) // Standard height
            .clip(RoundedCornerShape(28.dp)), // Fully rounded pill shape
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        },
        singleLine = true
    )
}

@Composable
fun CategoryRow() {
    val categories = listOf("Abstract", "Nature", "Anime", "Art", "Minimal")
    // Using available resources as placeholders for category backgrounds
    val images = listOf(
        R.drawable.wall1,
        R.drawable.wall2,
        R.drawable.wall3,
        R.drawable.wall4,
        R.drawable.wall5
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categories.size) { index ->
            CategoryItem(name = categories[index], imageRes = images[index % images.size])
        }
    }
}

@Composable
fun CategoryItem(name: String, imageRes: Int) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(50)), // Pill shape
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = imageRes,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        // Dark overlay for text readability
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )
        Text(
            text = name,
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = guedfont,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ScrollIndicator() {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(4.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(Color.Black) // Subtle indicator matching design
    )
}



