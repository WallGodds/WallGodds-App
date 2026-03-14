package com.example.wallgodds.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.HintGray
import com.example.wallgodds.ui.theme.UploadButtonActive
import com.example.wallgodds.ui.theme.UploadButtonDisabled
import com.example.wallgodds.ui.theme.poppinsFontFamily
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadImagePage(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val hazeState = remember { HazeState() }
    
    var wallpaperName by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    var previewUri by remember { mutableStateOf<Uri?>(null) }
    
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { previewUri = it }
    }

    val categories = listOf(
        "Abstract", "Nature", "Anime", "Art", "Movies", "Vehicles",
        "Sports", "Gaming", "Travels", "Spiritual", "Music", "AI Gen"
    )

    val isButtonActive = wallpaperName.isNotEmpty() && selectedCategory.isNotEmpty()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .hazeSource(state = hazeState)
    ) {
        // Background
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        val contentScrollState = rememberScrollState()

        // Main content (responsive sizing; avoids absolute offsets that break on devices)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 12.dp)
                .padding(bottom = 120.dp)
                .verticalScroll(contentScrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 36.dp)
                    .fillMaxWidth()
                    .heightIn(min = 360.dp, max = 520.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(Color.White.copy(alpha = 0.68f))
                    .clickable { launcher.launch("image/*") }
            ) {
                if (previewUri != null) {
                    AsyncImage(
                        model = previewUri,
                        contentDescription = "Preview",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Tap to upload image",
                            fontFamily = poppinsFontFamily,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Wallpaper name
                Box(
                    modifier = Modifier
                        .weight(1.35f)
                        .height(56.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .hazeEffect(
                            state = hazeState,
                            style = HazeStyle(
                                blurRadius = 50.dp,
                                tints = listOf(HazeTint(color = Color(0x33FFFFFF)))
                            )
                        )
                        .background(Color.White.copy(alpha = 0.68f)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    BasicTextField(
                        value = wallpaperName,
                        onValueChange = { wallpaperName = it },
                        singleLine = true,
                        textStyle = TextStyle(
                            fontFamily = poppinsFontFamily,
                            fontSize = 14.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 14.dp)
                    ) { innerTextField ->
                        if (wallpaperName.isEmpty()) {
                            Text(
                                text = "Wallpaper name",
                                fontFamily = poppinsFontFamily,
                                fontSize = 14.sp,
                                color = HintGray
                            )
                        }
                        innerTextField()
                    }
                }

                // Category dropdown (scrollable)
                Box(
                    modifier = Modifier
                        .weight(0.85f)
                        .height(56.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(18.dp))
                            .hazeEffect(
                                state = hazeState,
                                style = HazeStyle(
                                    blurRadius = 50.dp,
                                    tints = listOf(HazeTint(color = Color(0x33FFFFFF)))
                                )
                            )
                            .background(Color.White.copy(alpha = 0.68f))
                            .clickable { expanded = true }
                            .padding(horizontal = 14.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = selectedCategory.ifEmpty { "Category" },
                                fontFamily = poppinsFontFamily,
                                fontSize = 14.sp,
                                color = if (selectedCategory.isEmpty()) HintGray else Color.Black
                            )
                            Text(text = "▼", fontSize = 10.sp, color = HintGray)
                        }
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .heightIn(max = 280.dp)
                            .background(Color.White, RoundedCornerShape(18.dp)),
                        shape = RoundedCornerShape(18.dp),
                        containerColor = Color.White
                    ) {
                        categories.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption, fontFamily = poppinsFontFamily) },
                                onClick = {
                                    selectedCategory = selectionOption
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Upload button
            Box(
                modifier = Modifier
                    .padding(horizontal = 72.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            blurRadius = if (isButtonActive) 50.dp else 4.dp,
                            tints = listOf(HazeTint(color = Color.Transparent))
                        )
                    )
                    .background(
                        if (isButtonActive) UploadButtonActive.copy(alpha = 0.75f)
                        else UploadButtonDisabled
                    )
                    .clickable(enabled = isButtonActive) {
                        // No actual upload action needed per requirement
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Upload",
                    fontFamily = poppinsFontFamily,
                    fontSize = 16.sp,
                    color = if (isButtonActive) Color.White else Color.Black.copy(alpha = 0.4f)
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 900)
@Composable
fun UploadImagePagePreview() {
    UploadImagePage(rememberNavController())
}
