package com.oleksiikravchuk.littlelemon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oleksiikravchuk.littlelemon.R
import com.oleksiikravchuk.littlelemon.ui.theme.mainTypography
import com.oleksiikravchuk.littlelemon.ui.theme.primaryGreen
import com.oleksiikravchuk.littlelemon.ui.theme.primaryYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding() {

    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var emailAddress by remember {
        mutableStateOf("")
    }

    Column(Modifier.fillMaxWidth()) {
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
                .padding(16.dp, 32.dp, 16.dp, 0.dp)
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
                    .padding(bottom = 28.dp)
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
                    .padding(bottom = 28.dp)
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
                    .padding(bottom = 28.dp)
            )

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = primaryYellow),
                modifier = Modifier.fillMaxWidth(),
                ) {
                Text(text = "Register")
            }
        }
    }
}

@Composable
fun Header() {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.little_lemon_logo),
            contentDescription = stringResource(R.string.lemon_logo_description),
            modifier = Modifier
                .padding(vertical = 32.dp)
                .width(180.dp)
        )
    }
}


@Preview
@Composable
fun OnboardPreview() {
    Onboarding()
}