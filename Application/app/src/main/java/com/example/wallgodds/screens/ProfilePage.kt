package com.example.wallgodds.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.Lavender
import com.example.wallgodds.ui.theme.SoftBlush
import com.example.wallgodds.ui.theme.SoftLight
import com.example.wallgodds.ui.theme.SoftPink
import com.example.wallgodds.ui.theme.SoftYellow

@Composable
fun ProfilePageScreen(
    onBackPressed: () -> Unit,
    onDarkModeClicked: () -> Unit,
    onFeedbackClicked: () -> Unit = {},
    onLogoutClicked: () -> Unit = {},
    onDeleteAccountClicked: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // *** Use this as the background for your page ***
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        // Back Button
        IconButton(
            onClick = onBackPressed,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 12.dp, top = 12.dp)
        ) {
            Icon(painter = painterResource(id =R.drawable.baseline_arrow_back_ios_24), contentDescription = "Back", tint = Color.Black)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppPadding.MainContentPadding)
                .padding(top = 120.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Image
            Image(
                painter = painterResource(id = R.drawable.profile_pic),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.size(12.dp))

            Text(
                text = "Chrish Leo",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color(0xFF232323)
            )

            Spacer(modifier = Modifier.size(32.dp))

            OutlinedButton(
                onClick = onDarkModeClicked,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = AppPadding.Smallest),
                border = ButtonDefaults.outlinedButtonBorder,
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        "Dark Mode",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(start = AppPadding.Small)
                    )
                }
            }
            OutlinedButton(
                onClick = onFeedbackClicked,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = AppPadding.Smallest),
                border = ButtonDefaults.outlinedButtonBorder,
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        "Feedback & Bugs",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(start = AppPadding.Small)
                    )
                }
            }
            // Log Out Button
            Button(
                onClick = onLogoutClicked,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = AppPadding.Smallest),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4287F5), contentColor = Color.White)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        "Log Out",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(start = AppPadding.Small)
                    )
                }
            }
            // Delete Account Button
            Button(
                onClick = onDeleteAccountClicked,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = AppPadding.Smallest),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5621), contentColor = Color.White)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        "Delete Account",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(start = AppPadding.Small)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProfilePageScreenPreview() {
    ProfilePageScreen(
        onBackPressed = {},
        onDarkModeClicked = {},
        onFeedbackClicked = {},
        onLogoutClicked = {},
        onDeleteAccountClicked = {}
    )
}