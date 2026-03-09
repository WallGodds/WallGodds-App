package com.example.wallgodds.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppPadding


private val poppinsfont = FontFamily(
    Font(R.font.poppins_regular)
)
private val poppinsfontmed = FontFamily(
    Font(R.font.poppins_medium)
)

@Composable
fun ProfilePage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppPadding.MainContentPadding),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Code here

        Spacer(modifier = Modifier.height(60.dp))


        Box(
            modifier = Modifier
                .size(220.dp)
                .clip(RoundedCornerShape(27.dp))
                .background(Color(0xFFEAE6F4))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(190.dp)
                    .clip(RoundedCornerShape(16.75.dp))
                    .background(Color(0xFF7D73A4))
            ) {
                AsyncImage(
                    model = R.drawable.avatar,
                    contentDescription = "Profile Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))


        Text(
            text = "Aryan Roy",
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            fontFamily = poppinsfontmed,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))


        ProfileMenuItem(
            drawableRes = R.drawable.moon,
            text = "Dark Mode",
            textColor = Color(0xFF6B7BFF),
            iconTint = Color.Black,
            onClick = {  }
        )

        Spacer(modifier = Modifier.height(12.dp))

        ProfileMenuItem(
            drawableRes = R.drawable.bug,
            text = "Feedback & Bugs",
            textColor = Color(0xFF6B7BFF),
            iconTint = Color.Black,
            onClick = {  }
        )

        Spacer(modifier = Modifier.height(12.dp))

        ProfileMenuItem(
            drawableRes = R.drawable.star,
            text = "Rate Us",
            textColor = Color(0xFF6B7BFF),
            iconTint = Color.Black,
            onClick = {  }
        )

        Spacer(modifier = Modifier.height(12.dp))


        ProfileMenuItem(
            drawableRes = R.drawable.logout,
            text = "Log Out",
            backgroundColor = Color(0xFF6B7BFF),
            textColor = Color.White,
            iconTint = Color.White,
            onClick = {  }
        )

        Spacer(modifier = Modifier.height(12.dp))


        ProfileMenuItem(
            drawableRes = R.drawable.delete,
            text = "Delete Account",
            backgroundColor = Color(0xFFFF8A80),
            textColor = Color.White,
            iconTint = Color.White,
            onClick = {}
        )

    }
}






@Composable
fun ProfileMenuItem(
    drawableRes: Int,
    text: String,
    backgroundColor: Color = Color(0xFFF5F7FF),
    textColor: Color = Color(0xFF6B7BFF),
    iconTint: Color = Color(0xFF6B7BFF),
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .width(340.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = drawableRes),
                contentDescription = text,
                tint = iconTint,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                fontFamily = poppinsfont,
                color = textColor
            )
        }
    }
}