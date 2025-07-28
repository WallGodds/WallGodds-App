package com.example.wallgodds.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.wallgodds.R
import com.example.wallgodds.navigation.Routes
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.AppSize

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    navController: NavController
){
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = AppPadding.Medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.wallgodds_icon),
                contentDescription = "WallGodds Icon",
                modifier = Modifier
                    .size(AppSize.IconMedium)
            )
            Image(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = "Profile Icon",
                modifier = Modifier
                    .size(AppSize.IconMedium)
                    .clickable { navController.navigate(Routes.profile_page) }
            )
        }
    }
}