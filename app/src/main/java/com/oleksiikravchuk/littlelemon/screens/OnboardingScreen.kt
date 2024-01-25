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
import com.oleksiikravchuk.littlelemon.ui.theme.LittleLemonTheme
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
                .background(color = MaterialTheme.colorScheme.primary),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.onboarding_greating),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(vertical = 40.dp)
            )
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Personal information",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "First Name",
                style = MaterialTheme.typography.titleSmall,
            )
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = "Last Name",
                style = MaterialTheme.typography.titleSmall,
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = "Email",
                style = MaterialTheme.typography.titleSmall,
            )
            OutlinedTextField(
                value = emailAddress,
                onValueChange = { emailAddress = it },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Button(
                onClick = { showDialog = true },
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Register",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
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

    LittleLemonTheme {
        val navHostController = rememberNavController()
        OnboardingScreen(navHostController)
    }
}