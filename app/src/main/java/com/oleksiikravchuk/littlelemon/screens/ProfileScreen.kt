package com.oleksiikravchuk.littlelemon.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.oleksiikravchuk.littlelemon.Onboarding
import com.oleksiikravchuk.littlelemon.R
import com.oleksiikravchuk.littlelemon.ui.theme.LittleLemonTheme


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
    ) {
        ProfileHeader(onBackClicked = { navController.popBackStack() })

        Box(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.inversePrimary),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Avatar Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .size(150.dp)
                    .clip(CircleShape)
            )
        }

        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Personal Information",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Text(
                text = "First Name",
                style = MaterialTheme.typography.titleSmall,
            )

            OutlinedTextField(
                value = firstName.toString(),
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Last Name",
                style = MaterialTheme.typography.titleSmall,
            )

            OutlinedTextField(
                value = lastName.toString(),
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Email",
                style = MaterialTheme.typography.titleSmall,
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
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Log out",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }


    }
}

@Composable
fun ProfileHeader(onBackClicked: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            Icons.Rounded.ArrowBackIosNew,
            contentDescription = "",
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onBackClicked() }
                .background(MaterialTheme.colorScheme.inversePrimary)
                .padding(8.dp)
                .size(28.dp),
            tint = MaterialTheme.colorScheme.primary

        )

        Image(
            painter = painterResource(id = R.drawable.little_lemon_logo),
            contentDescription = stringResource(R.string.lemon_logo_description),
            modifier = Modifier
                .width(150.dp)

        )

        Spacer(modifier = Modifier.width(30.dp))
    }
}


@Preview
@Composable
fun ProfilePreview() {
    LittleLemonTheme {
        val navHostController = rememberNavController()
        ProfileScreen(navHostController)
    }

}
