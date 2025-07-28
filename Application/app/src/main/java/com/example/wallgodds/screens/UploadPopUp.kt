package com.example.wallgodds.screens

import android.net.Uri
import android.widget.Toast
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopUpPage(
    onProfileClick: () -> Unit = {},
    onClose:() -> Unit = {}
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var wallpaperName by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top Bar
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Row(
            Modifier.Companion
                .fillMaxWidth()
                .padding(28.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Companion.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.wallgodds_icon),
                contentDescription = "WallGodds Icon",
                modifier = Modifier.size(AppSize.IconMedium)
            )
            Image(
                painter = painterResource(R.drawable.profile_icon),
                contentDescription = "Profile Icon",
                modifier = Modifier
                    .size(AppSize.IconMedium)
                    .clickable { onProfileClick() }
            )
        }

        // Center Card
        Card(
            modifier = Modifier.Companion
                .align(Alignment.Companion.Center)
                .padding(28.dp)
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            elevation = CardDefaults.cardElevation(2.dp)

        ) {
            Box(Modifier.Companion.padding(12.dp)) {
                Column(horizontalAlignment = Alignment.Companion.CenterHorizontally) {

                    // Close Button (top-right inside card)
                    Box(modifier = Modifier.Companion.fillMaxWidth()) {
                        IconButton(
                            onClick = { onClose()},
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(0.dp)
                                .size(24.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Red, shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Close",
                                    tint = Color.White,
                                    modifier = Modifier.Companion.size(20.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.Companion.height(56.dp))

                    // upload Image

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable{
                                imagePickerLauncher.launch("image/*")
                            }
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.upload_button_image),
                            contentDescription = "Insert Image",
                            modifier = Modifier.size(AppSize.IconMedium)
                        )
                        Text(
                            text = "Insert Image",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.Companion.height(64.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 0.dp)
                    ) {
                        // label above TextField
                        Text(
                            text = "Wallpaper Name",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(32.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(horizontal = 12.dp, vertical = 4.dp), // internal padding
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (wallpaperName.isEmpty()) {
                                Text(
                                    text = "Enter Your Wallpaper Name",
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontSize = 16.sp
                                )
                            }
                            BasicTextField(
                                value = wallpaperName,
                                onValueChange = { wallpaperName = it },
                                singleLine = true,
                                textStyle = MaterialTheme.typography.bodyLarge.copy(
                                    color = Color.Black
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.Companion.height(18.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 0.dp)
                    ) {

                        Column(modifier = Modifier.fillMaxWidth()) {
                            // Label
                            Text(
                                text = "Category",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )

                            ExposedDropdownMenuBox(
                                expanded = expanded,
                                onExpandedChange = { expanded = !expanded }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .menuAnchor()
                                        .fillMaxWidth()
                                        .height(32.dp)
                                        .border(1.dp, Color.Gray, RoundedCornerShape(13.dp))
                                        .padding(horizontal = 12.dp, vertical = 4.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Text(
                                        text = if (selectedCategory.isNotEmpty()) selectedCategory else "Choose Your Categorie",
                                        color = if (selectedCategory.isNotEmpty()) Color.Black else Color.Gray,
                                        fontSize = 16.sp,
                                        style = MaterialTheme.typography.bodyLarge,
                                    )

                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.CenterEnd)
                                            .size(20.dp)
                                            .background(Color(0xFF1A95F6), shape = CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ArrowDropDown,
                                            contentDescription = "Dropdown",
                                            tint = Color.White,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }

                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false },
                                    modifier = Modifier.exposedDropdownSize()
                                ) {
                                    listOf("Abstract", "Nature", "Anime", "Art", "Movies", "Vehicles", "Sports", "Games", "Travel", "Spiritual", "Music", "AIGen").forEach { option ->
                                        DropdownMenuItem(
                                            text = { Text(text = option, fontFamily = FontFamily.Cursive , fontSize = 18.sp) },
                                            onClick = {
                                                selectedCategory = option
                                                expanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        }

                    }

                    Spacer(modifier = Modifier.Companion.height(18.dp))

                    // submit button
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {
                                if (selectedImageUri != null && wallpaperName.isNotBlank() && selectedCategory.isNotEmpty()) {
                                    Toast.makeText(context, "Submitted successfully!", Toast.LENGTH_SHORT).show()
                                    onClose() // Calls finish() to close the popup
                                } else {
                                    Toast.makeText(context, "Please fill all fields!", Toast.LENGTH_SHORT).show()
                                }
                            },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A95F6)),
                            modifier = Modifier
                                .fillMaxWidth(0.45f)
                                .height(34.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Submit",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 22.sp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.Companion.height(24.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PopUpPagePreview() {
    com.example.wallgodds.ui.theme.WallGoddsTheme {
        PopUpPage(
            onProfileClick =  {},
            onClose = {}
        )
    }
}