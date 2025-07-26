package com.example.wallgodds.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallgodds.R
import com.example.wallgodds.navigation.Routes
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.AppSize



@Composable
fun UploadScreen(navController: NavController) {

	val availableWallpapers = listOf(
		R.drawable.wallpaper2,
		R.drawable.wallpaper3,
		R.drawable.wallpaper4,
		R.drawable.wallpaper5
	)

	val imageList = remember {
		mutableStateOf(List(30) { index: Int -> availableWallpapers[index%availableWallpapers.size] })
	}


	Box(modifier = Modifier.fillMaxSize()){
		Image(
			painter = painterResource(R.drawable.background_image),
			contentDescription = "background",
			contentScale = ContentScale.Crop,
			modifier = Modifier.fillMaxSize()
		)
	}
	LazyColumn(
		modifier = Modifier
			.fillMaxSize(),
		contentPadding = PaddingValues(AppPadding.MainContentPadding),
		verticalArrangement = Arrangement.spacedBy(AppPadding.Medium)
	) {


		item {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(vertical = AppPadding.Medium),
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
		}


		item {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.aspectRatio(2f)
					.clip(RoundedCornerShape(AppSize.HighCornerRadius))
					.background(Color.White)
					.clickable {
						navController.navigate(Routes.home_page){
							popUpTo(Routes.upload_page){
								inclusive = true
							}
						}
					}
					.border(1.dp,Color.Gray, RoundedCornerShape(AppSize.HighCornerRadius)),
				contentAlignment = Alignment.Center
			) {
				Column(
					horizontalAlignment = Alignment.CenterHorizontally,
					verticalArrangement = Arrangement.Center,
					modifier = Modifier.padding(AppPadding.Small)
				) {
					Image(
						painter = painterResource(R.drawable.button_image),
						contentDescription = "Upload",
						modifier = Modifier
							.size(AppSize.IconLarge)
					)
					Spacer(modifier = Modifier.height(AppPadding.Smallest))
					Text(
						text = "Upload Your Wallpaper",
						style = MaterialTheme.typography.bodyMedium
					)
				}
			}
		}


		item {
			Spacer(modifier = Modifier.height(AppPadding.Medium))
		}


		item {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.height(600.dp)
			) {
				LazyVerticalGrid(
					columns = GridCells.Fixed(3),
					userScrollEnabled = false,
					verticalArrangement = Arrangement.spacedBy(AppPadding.Small),
					horizontalArrangement = Arrangement.spacedBy(AppPadding.Small),
					modifier = Modifier.fillMaxSize()
				) {
					items(imageList.value) { imageRes ->
						Image(
							painter = painterResource(imageRes),
							contentDescription = "Wallpaper",
							contentScale = ContentScale.Crop,
							modifier = Modifier
								.aspectRatio(9f / 16f)
								.clip(RoundedCornerShape(AppSize.WallpaperRoundedCorner))
						)
					}
				}
			}
		}

	}
}




