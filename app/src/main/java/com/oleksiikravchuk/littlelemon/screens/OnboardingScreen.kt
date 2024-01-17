package com.oleksiikravchuk.littlelemon.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.oleksiikravchuk.littlelemon.Home
import com.oleksiikravchuk.littlelemon.R
import com.oleksiikravchuk.littlelemon.components.Header
import com.oleksiikravchuk.littlelemon.ui.theme.mainTypography
import com.oleksiikravchuk.littlelemon.ui.theme.primaryGreen
import com.oleksiikravchuk.littlelemon.ui.theme.primaryYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(navController: NavHostController) {

    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var emailAddress by remember {
        mutableStateOf("")
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    Column(
        Modifier.fillMaxWidth(),
    ) {
        Header()
        Row(
            Modifier
                .fillMaxWidth()
                .background(color = primaryGreen),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.onboarding_greating),
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier.padding(vertical = 40.dp)
            )
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp, 32.dp, 16.dp, 0.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Personal information",
                color = mainTypography,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "First Name",
                color = mainTypography,
                fontSize = 14.sp,
                modifier = Modifier.padding(4.dp)
            )
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = "Last Name",
                color = mainTypography,
                fontSize = 14.sp,
                modifier = Modifier.padding(4.dp)
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = "Email Name",
                color = mainTypography,
                fontSize = 14.sp,
                modifier = Modifier.padding(4.dp)
            )
            OutlinedTextField(
                value = emailAddress,
                onValueChange = { emailAddress = it },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Button(
                onClick = { showDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = primaryYellow),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Register")
            }

            if (showDialog) {
                if (firstName.isBlank() || lastName.isBlank() || emailAddress.isBlank()) {
                    AlertDialog(
                        onDismissRequest = { },
                        title = { Text("Registration unsuccessful.") },
                        text = { Text("Please enter all data") },
                        confirmButton = {
                            Button(onClick = { showDialog = false }) {
                                Text(text = "Confirm")
                            }
                        })
                } else {
                    AlertDialog(
                        onDismissRequest = { },
                        title = { Text("Registration successful") },
                        text = { Text("Thank you for your registration") },
                        confirmButton = {

                            context.getSharedPreferences(
                                "Settings",
                                Context.MODE_PRIVATE
                            ).edit()
                                .putBoolean("isLoggedIn", true)
                                .putString("firstName", firstName)
                                .putString("lastName", lastName)
                                .putString("email", emailAddress).apply()

                            Button(onClick = {
                                showDialog = false
                                navController.navigate(Home.route)
                            }) {
                                Text(text = "Go to Home Screen")
                            }
                        }
                    )
                }
            }

        }
    }
}


@Preview
@Composable
fun OnboardPreview() {

    val navHostController = rememberNavController()
    OnboardingScreen(navHostController)
}