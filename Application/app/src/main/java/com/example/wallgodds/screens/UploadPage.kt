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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wallgodds.R
import com.example.wallgodds.navigation.Routes
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.AppSize
import com.example.wallgodds.utils.TopAppBar

// Upload Page
@Composable
fun UploadPage(navController: NavController) {
	val availableWallpapers = listOf(
		R.drawable.wallpaper2,
		R.drawable.wallpaper3,
		R.drawable.wallpaper4,
		R.drawable.wallpaper5
	)

	val imageList = remember {
		mutableStateOf(List(30) { index -> availableWallpapers[index % availableWallpapers.size] })
	}

	// Whole Screen is wrapped in a box for better alignment and component stacking
	Box(modifier = Modifier.fillMaxSize()) {

		Image(
			painter = painterResource(R.drawable.background),
			contentDescription = "background",
			contentScale = ContentScale.Crop,
			modifier = Modifier.fillMaxSize()
		)

		// The screen is divided into 3 sections - all three have been wrapped in LazyColumn to make them
		// scrollable
		LazyColumn(
			modifier = Modifier.fillMaxSize(),
			contentPadding = PaddingValues(AppPadding.MainContentPadding),
			verticalArrangement = Arrangement.spacedBy(AppPadding.PaddingBetweenCategories)
		) {

			// section 1- Top App Bar

			item { TopAppBar(navController=navController) }
			// section 2- Clickable Upload button to let users add their own wallpapers
			item {
				Box(
					modifier = Modifier
						.fillMaxWidth()
						.aspectRatio(2f)
						.clip(RoundedCornerShape(AppSize.HighCornerRadius))
						.background(Color.White)
						.clickable {
							navController.navigate(Routes.home_page) {
								popUpTo(Routes.upload_page) {
									inclusive = true
								}
							}
						}
						.border(1.dp, Color.Gray, RoundedCornerShape(AppSize.HighCornerRadius)),
					contentAlignment = Alignment.Center
				) {
					Column(
						horizontalAlignment = Alignment.CenterHorizontally,
						verticalArrangement = Arrangement.spacedBy(AppPadding.Smallest),
						modifier = Modifier.padding(AppPadding.Medium)
					) {
						Image(
							painter = painterResource(R.drawable.upload_button_image),
							contentDescription = "Upload",
							modifier = Modifier.size(AppSize.IconMedium)
						)

						Text(
							text = "Upload Your Wallpaper",
							color = Color.Black,
							style = MaterialTheme.typography.bodyLarge
						)
					}
				}
			}

			// Spacer -  for clean UI look
			item { Spacer(modifier = Modifier.height(AppPadding.PaddingBetweenCategories)) }

			// section 3 - Vertical grid layout of wallpapers with scrollable behaviour
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
}


@Preview(showBackground = true)
@Composable
fun UploadPagePreview (){
	UploadPage(
		navController = rememberNavController()
	)
}

