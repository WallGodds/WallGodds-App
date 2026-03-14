package com.example.wallgodds.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wallgodds.ui.theme.poppinsFontFamily

@Composable
fun WallpaperPreviewPage(
    navController: NavController,
    wallpaper :Int)
{
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(36.dp)
        .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Image(
            painter = painterResource(id = wallpaper),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(550.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )

    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ActionButton(
            icon = com.example.wallgodds.R.drawable.back_icon,
            label = "Back",
            onClick = {navController.popBackStack()},
        )
        ActionButton(
            icon = com.example.wallgodds.R.drawable.download_icon,
            label = "Download",
            onClick = {
                Toast.makeText(context, "Download started!", Toast.LENGTH_SHORT).show()
            },
        )
        ActionButton(
            icon = com.example.wallgodds.R.drawable.set_icon,
            label = "Set",
            onClick = { /* UI only - no action */ },
        )
    }
}
}



@Composable
private fun ActionButton(
    icon: Int,
     label : String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Color(0xBF7056F5)
                )
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(36.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color(0xBF7056F5),
            fontWeight = FontWeight.Medium,
            fontFamily = poppinsFontFamily,
        )
    }
}