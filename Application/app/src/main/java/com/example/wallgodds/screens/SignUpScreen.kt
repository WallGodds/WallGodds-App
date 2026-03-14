package com.example.wallgodds.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppSize
import com.example.wallgodds.ui.theme.poppinsFontFamily

@Composable
fun SignUpScreen(onSignUpClick: () -> Unit = {}) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            // Header
            Text(
                text = "Sign Up",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Create an account to save favorites, upload\nwallpapers, and personalize your experience",
                fontFamily = poppinsFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White.copy(alpha = 0.85f),
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Social Sign-Up Buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SocialButton(text = "Google", modifier = Modifier.weight(1f)) { GoogleIcon() }
                SocialButton(text = "Microsoft", modifier = Modifier.weight(1f)) { MicrosoftIcon() }
            }

            Spacer(modifier = Modifier.height(8.dp))

            SocialButton(
                text = "GitHub",
                modifier = Modifier.fillMaxWidth(0.52f)
            ) { GitHubIcon() }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "or",
                fontFamily = poppinsFontFamily,
                fontSize = 16.sp,
                color = Color(0xFF5A5A5A).copy(alpha = 0.8f),
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input Fields
            SignUpTextField(label = "Email *")
            Spacer(modifier = Modifier.height(12.dp))

            SignUpTextField(label = "User name *")
            Spacer(modifier = Modifier.height(12.dp))

            SignUpTextField(
                label = "Password *",
                visualTransformation = PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(12.dp))

            SignUpTextField(
                label = "Confirm password *",
                visualTransformation = PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Sign Up Button
            Button(
                onClick = { onSignUpClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6B7BFF)
                )
            ) {
                Text(
                    text = "Sign Up",
                    fontFamily = poppinsFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Sign-In Redirect
            val annotatedText = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color(0xFF6B6B9E),
                    fontFamily = poppinsFontFamily
                )) {
                    append("Already have an Account ? ")
                }
                withStyle(style = SpanStyle(
                    color = Color(0xFF6B7BFF),
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFontFamily
                )) {
                    append("Sign in here")
                }
            }
            Text(
                text = annotatedText,
                fontSize = AppSize.FontSizeSmall,
                modifier = Modifier.clickable { /* No logic */ }
            )

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun SocialButton(
    text: String,
    modifier: Modifier = Modifier,
    iconContent: @Composable () -> Unit
) {
    Surface(
        modifier = modifier
            .height(42.dp),
        shape = RoundedCornerShape(14.dp),
        color = Color(0xFFD0D9FF),
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            iconContent()
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                fontFamily = poppinsFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF3C4043)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpTextField(
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    var textValue by remember { mutableStateOf("") }

    OutlinedTextField(
        value = textValue,
        onValueChange = { textValue = it },
        placeholder = {
            Text(
                text = label,
                fontFamily = poppinsFontFamily,
                color = Color(0xFF7B6BA5),
                fontSize = AppSize.FontSizeSmall
            )
        },
        textStyle = TextStyle(
            fontFamily = poppinsFontFamily,
            fontSize = AppSize.FontSizeSmall,
            color = Color(0xFF3A3A6A)
        ),
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White.copy(alpha = 0.55f),
            unfocusedContainerColor = Color.White.copy(alpha = 0.45f),
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            unfocusedPlaceholderColor = Color(0xFF7B6BA5),
            focusedPlaceholderColor = Color(0xFF7B6BA5),
            cursorColor = Color(0xFF755CFF)
        ),
        singleLine = true
    )
}

// Official provider logos from vector drawables
@Composable
fun GoogleIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_google),
        contentDescription = "Google",
        modifier = Modifier.size(28.dp)
    )
}

@Composable
fun MicrosoftIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_microsoft),
        contentDescription = "Microsoft",
        modifier = Modifier.size(28.dp)
    )
}

@Composable
fun GitHubIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_github),
        contentDescription = "GitHub",
        modifier = Modifier.size(28.dp)
    )
}


