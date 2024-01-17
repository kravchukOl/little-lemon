package com.oleksiikravchuk.littlelemon.screens

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.oleksiikravchuk.littlelemon.Home
import com.oleksiikravchuk.littlelemon.Onboarding
import com.oleksiikravchuk.littlelemon.components.Header
import com.oleksiikravchuk.littlelemon.ui.theme.mainTypography
import com.oleksiikravchuk.littlelemon.ui.theme.primaryYellow
import com.oleksiikravchuk.littlelemon.ui.theme.secondaryOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController) {

    val context = LocalContext.current

    val firstName by lazy {
        context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
            .getString("firstName", "none")
    }

    val lastName by lazy {
        context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
            .getString("lastName", "none")
    }

    val email by lazy {
        context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
            .getString("email", "none")
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp, 16.dp, 16.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Header()

        Text(
            text = "Personal Information",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 25.dp, bottom = 25.dp),
            color = mainTypography
        )

        Text(
            text = "First Name",
            color = mainTypography,
            fontSize = 14.sp,
        )

        OutlinedTextField(
            value = firstName.toString(),
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Last Name",
            color = mainTypography,
            fontSize = 14.sp,
        )

        OutlinedTextField(
            value = lastName.toString(),
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Email",
            color = mainTypography,
            fontSize = 14.sp,
        )

        OutlinedTextField(
            value = email.toString(),
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )


        Button(
            onClick = {
                context.getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
                    .putBoolean("isLoggedIn", false)
                    .apply()
                navController.navigate(Onboarding.route)
            },
            colors = ButtonDefaults.buttonColors(containerColor = primaryYellow),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Log out")
        }
    }
}


@Preview
@Composable
fun ProfilePreview() {
    val navHostController = rememberNavController()
    ProfileScreen(navHostController)
}
