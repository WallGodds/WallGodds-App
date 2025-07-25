package com.example.wallgodds.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.AppSize


@Composable
fun UploadScreen() {
	val uploadPageGradient = Brush.verticalGradient(
		colors = listOf(
			Color(0xFFD0BCFF),
			Color(0xFFCCC2DC),
	        Color(0xFFEFB8C8)
		)
	)
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(uploadPageGradient),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = AppPadding.MainContentPadding, vertical = AppPadding.Medium),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Image(
				painter = painterResource(R.drawable.wallgodds_icon),
				contentDescription = "WallGodds Icon",
				modifier = Modifier.size(AppSize.IconMedium)
			)
			Image(
				painter = painterResource(R.drawable.profile_icon),
				contentDescription = "Profile Icon",
				modifier = Modifier.size(AppSize.IconMedium)
			)
		}
		Box(
			Modifier
				.fillMaxWidth()
				.padding(
					horizontal = AppPadding.MainContentPadding, // 24.dp
					vertical = AppPadding.Medium // 16.dp
				)
				.aspectRatio(16f/9f) // or height = 180.dp if you prefer fixed height
				.clip(RoundedCornerShape(AppSize.HighCornerRadius)) // use consistent radius if defined
				.border(1.dp, Color.Gray, RoundedCornerShape(AppSize.HighCornerRadius))
				.background(Color.White)
				.clickable { /* your action */ },
			contentAlignment = Alignment.Center

		) {
			Column (
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
			){
				Image(
					painter = painterResource(R.drawable.upload_image),
					contentDescription = "Upload-button-image",
					modifier = Modifier
						.fillMaxWidth()
						.clip(RoundedCornerShape(12.dp))

				)
				Spacer(modifier = Modifier.height(8.dp))
				Text(
					text = "Upload Your Wallpaper",
					style = MaterialTheme.typography.titleMedium
				)
			}

		}
	}
}