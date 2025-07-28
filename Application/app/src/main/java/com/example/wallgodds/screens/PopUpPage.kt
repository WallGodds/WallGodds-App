package com.example.wallgodds.screens

import android.os.Bundle
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

class PopUpPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTestScreen()
        }
    }
}

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp), // internal padding
        contentAlignment = Alignment.CenterStart
    ) {
        if (value.isEmpty()) {
            Text(
                text = placeholder,
                color = Color.Gray,
                fontFamily = FontFamily.Serif,
                fontSize = 14.sp
            )
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = Color.Black,
                fontFamily = FontFamily.Serif
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdown(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        // Label
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Serif,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            Box(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .height(36.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(13.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = if (selectedOption.isNotEmpty()) selectedOption else "Choose Your Category",
                    color = if (selectedOption.isNotEmpty()) Color.Black else Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Serif
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
                modifier = Modifier
                    .exposedDropdownSize()
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTestScreen(onProfileClick: () -> Unit = {}) {
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
                            onClick = { /* TODO: Close action */ },
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

                    // Insert Image Icon
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = "Insert Image",
                        modifier = Modifier.Companion.size(48.dp)
                    )
                    Text(
                        text = "Insert Image",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.Companion.height(64.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 0.dp)
                    ) {
                        // label above TextField
                        Text(
                            text = "Wallpaper Name",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.Serif,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        var wallpaperName by remember { mutableStateOf("") }

                        CustomOutlinedTextField(
                            value = wallpaperName,
                            onValueChange = { wallpaperName = it },
                            placeholder = "Enter Your Wallpaper Name"
                        )

                    }

                    Spacer(modifier = Modifier.Companion.height(18.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 0.dp)
                    ) {
                        var selectedCategory by remember { mutableStateOf("") }

                        CustomDropdown(
                            label = "Category",
                            options = listOf("Abstract", "Nature", "Anime", "Art", "Movies", "Vehicles", "Sports", "Games", "Travel", "Spiritual", "Music", "AIGen"),
                            selectedOption = selectedCategory,
                            onOptionSelected = { selectedCategory = it }
                        )
                    }

                    Spacer(modifier = Modifier.Companion.height(18.dp))

                    // Submit Button
                    Button(
                        onClick = { /* TODO: Handle submission */ },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A95F6)),
                        modifier = Modifier
                            .width(140.dp)
                            .height(36.dp)
                    ) {
                        Text(
                            text = "Submit",
                            color = Color.Companion.White,
                            fontFamily = FontFamily.Serif,
                            fontSize = 20.sp,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                    Spacer(modifier = Modifier.Companion.height(24.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyTestScreenPreview() {
    MyTestScreen()
}
