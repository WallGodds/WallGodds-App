package com.example.wallgodds.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.wallgodds.R
import com.example.wallgodds.ui.components.SearchBar
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.poppinsFontFamily
import com.example.wallgodds.wallpapers.wallpapers

@Composable
fun FavoritesPageScreen(navController: NavController) {
    // Taking images from the wallpapers list as requested
    val favoriteWallpapers = wallpapers.take(10)
    var sortOption by remember { mutableStateOf("Newest") }
    var sortExpanded by remember { mutableStateOf(false) }
    val isEmpty = false
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppPadding.MainContentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Search and Filter Row
        Row(modifier = Modifier.fillMaxWidth()) {
            SearchBar(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Box {
                OutlinedButton(
                    onClick = { sortExpanded = true },
                    modifier = Modifier
                        .width(100.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    contentPadding = PaddingValues(horizontal = 6.dp, vertical = 0.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White.copy(alpha = 0.6f)
                    ),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = sortOption,
                            color = Color(0xFF929292),
                            fontSize = 15.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = Color(0xFF929292),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                DropdownMenu(
                    expanded = sortExpanded,
                    onDismissRequest = { sortExpanded = false },
                    modifier = Modifier.background(Color.White.copy(alpha = 0.6f))
                ) {
                    DropdownMenuItem(
                        text = { Text("Newest", fontFamily = poppinsFontFamily) },
                        onClick = { sortOption = "Newest"; sortExpanded = false }
                    )
                    DropdownMenuItem(
                        text = { Text("Oldest", fontFamily = poppinsFontFamily) },
                        onClick = { sortOption = "Oldest"; sortExpanded = false }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isEmpty) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                EmptyState()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                // Padding at bottom to ensure images aren't cut off by nav bar
                contentPadding = PaddingValues(bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(favoriteWallpapers) { wallpaper ->
                    AsyncImage(
                        model = wallpaper,
                        contentDescription = "Favourite Wallpaper",
                        contentScale = ContentScale.Crop, // Vital for filling the 260dp height
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(360.dp) // Adjusted height to match Figma's elongated look
                            .clip(RoundedCornerShape(24.dp)) // Increased radius to match template
                            .background(Color.LightGray)
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyState() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.favourite_page_guy),
            contentDescription = null,
            modifier = Modifier.size(170.dp, 160.dp)
        )
        Text(
            text = "No favourites yet.",
            fontSize = 20.sp,
            fontFamily = poppinsFontFamily,
            color = Color(0xFF808080)
        )
        Text(
            text = "Start saving wallpapers you like.",
            fontSize = 16.sp,
            fontFamily = poppinsFontFamily,
            color = Color(0xFF808080)
        )
    }
}