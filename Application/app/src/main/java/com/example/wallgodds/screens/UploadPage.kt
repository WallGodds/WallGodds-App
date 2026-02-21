package com.example.wallgodds.screens



import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.poppinsFontFamily
import com.example.wallgodds.utils.dashedBorder

@Composable
fun UploadPage(navController: NavController) {

    var uploadedImages by remember {
        mutableStateOf<List<Uri>>(
            listOf(
                Uri.parse("android.resource://com.example.wallgodds/${R.drawable.wall1}"),
                Uri.parse("android.resource://com.example.wallgodds/${R.drawable.wall2}"),
                Uri.parse("android.resource://com.example.wallgodds/${R.drawable.wall3}"),
                Uri.parse("android.resource://com.example.wallgodds/${R.drawable.wall4}"),
                Uri.parse("android.resource://com.example.wallgodds/${R.drawable.wall5}"),
                Uri.parse("android.resource://com.example.wallgodds/${R.drawable.wall6}"),

                )
        )
    }
    var previewUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { uploadedImages = uploadedImages + it }
    }

    previewUri?.let { uri ->
        Dialog(
            onDismissRequest = { previewUri = null },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .clickable { previewUri = null },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = "Preview",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }

    if (uploadedImages.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppPadding.MainContentPadding)
                .padding(top = 12.dp, bottom = 90.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            UploadBox { launcher.launch("image/*") }

            Spacer(modifier = Modifier.height(180.dp))

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
    } else {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppPadding.MainContentPadding),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalItemSpacing = 12.dp,
            contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp)
        ) {

            item {
                UploadBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                ) { launcher.launch("image/*") }
            }


            items(uploadedImages) { uri ->
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .clickable {},
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


@Composable
fun UploadBox(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(260.dp),
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0x85FFFFFF))
            .dashedBorder(
                strokeWidth = 2.dp,
                color = Color(0xFF8EA3E6),
                cornerRadius = 24.dp,
                dashLength = 8.dp,
                gapLength = 8.dp
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                fontFamily = poppinsFontFamily,
                color = Color(0xFF3C56B1)
            )
        }
    }
}