package com.example.wallgodds.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.poppinsFontFamily
import com.example.wallgodds.utils.dashedBorder

// List of 12 wallpaper categories
private val WALLPAPER_CATEGORIES = listOf(
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
    "AIGen"
)

@Composable
fun UploadPage(navController: NavController) {

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var wallpaperName by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var expandedDropdown by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    // Check if upload button should be enabled
    val isUploadButtonEnabled = wallpaperName.isNotEmpty() && selectedCategory.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppPadding.MainContentPadding)
            .padding(bottom = 90.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        // Image Preview or Upload Placeholder
        if (selectedImageUri != null) {
            // Image Preview Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFF8F6FF))
                    .clickable { launcher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(selectedImageUri),
                    contentDescription = "Selected Wallpaper Preview",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(AppPadding.Medium))

            // Wallpaper Name TextField
            OutlinedTextField(
                value = wallpaperName,
                onValueChange = { wallpaperName = it },
                label = {
                    Text(
                        "Wallpaper name",
                        fontSize = 14.sp,
                        fontFamily = poppinsFontFamily
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF8F6FF),
                    unfocusedContainerColor = Color(0xFFF8F6FF),
                    focusedBorderColor = Color(0xFF407BFF),
                    unfocusedBorderColor = Color(0xFFDCDCDC),
                    focusedLabelColor = Color(0xFF407BFF),
                    unfocusedLabelColor = Color(0xFF808080)
                ),
                textStyle = androidx.compose.material3.LocalTextStyle.current.copy(
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily,
                    color = Color.Black
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(AppPadding.Medium))

            // Category Dropdown
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = selectedCategory,
                    onValueChange = {},
                    label = {
                        Text(
                            "Category",
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clickable { expandedDropdown = !expandedDropdown },
                    shape = RoundedCornerShape(16.dp),
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Dropdown",
                            tint = Color(0xFF407BFF),
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { expandedDropdown = !expandedDropdown }
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF8F6FF),
                        unfocusedContainerColor = Color(0xFFF8F6FF),
                        focusedBorderColor = Color(0xFF407BFF),
                        unfocusedBorderColor = Color(0xFFDCDCDC),
                        focusedLabelColor = Color(0xFF407BFF),
                        unfocusedLabelColor = Color(0xFF808080)
                    ),
                    textStyle = androidx.compose.material3.LocalTextStyle.current.copy(
                        fontSize = 14.sp,
                        fontFamily = poppinsFontFamily,
                        color = Color.Black
                    ),
                    singleLine = true
                )

                // Dropdown Menu
                DropdownMenu(
                    expanded = expandedDropdown,
                    onDismissRequest = { expandedDropdown = false },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .align(Alignment.TopCenter)
                ) {
                    WALLPAPER_CATEGORIES.forEach { category ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    category,
                                    fontSize = 14.sp,
                                    fontFamily = poppinsFontFamily,
                                    color = Color.Black
                                )
                            },
                            onClick = {
                                selectedCategory = category
                                expandedDropdown = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(AppPadding.Large))

            // Upload Button with State Validation
            Button(
                onClick = {
                    // No actual upload action required per requirements
                    // Button is just for UI demonstration
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isUploadButtonEnabled) Color(0xFF407BFF) else Color(0xFFB0C4FF),
                    disabledContainerColor = Color(0xFFB0C4FF)
                ),
                enabled = isUploadButtonEnabled
            ) {
                Text(
                    "Upload",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFontFamily,
                    color = Color.White
                )
            }

        } else {
            // Empty State - Upload Placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .clip(RoundedCornerShape(46.dp))
                    .background(Color(0x85FFFFFF)) // #FFFFFF 52%
                    .dashedBorder(
                        strokeWidth = 2.dp,
                        color = Color(0xFF8EA3E6),
                        cornerRadius = 46.dp,
                        dashLength = 8.dp,
                        gapLength = 8.dp
                    )
                    .clickable { launcher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(53.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFF2F5FF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.upload_icon),
                            contentDescription = "Upload Icon",
                            tint = Color(0xFF8EA3E6).copy(alpha = 0.75f),
                            modifier = Modifier.size(45.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Upload an Image",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = poppinsFontFamily,
                        color = Color(0xFF3C56B1)
                    )
                }
            }

            Spacer(modifier = Modifier.height(180.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Nothing here yet.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFontFamily,
                    color = Color(0xFF808080)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Upload a wallpaper to share your creativity.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = poppinsFontFamily,
                    color = Color(0xFF808080),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}



