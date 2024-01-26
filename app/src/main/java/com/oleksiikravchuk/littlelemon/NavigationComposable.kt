package com.oleksiikravchuk.littlelemon

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oleksiikravchuk.littlelemon.screens.HomeScreen
import com.oleksiikravchuk.littlelemon.screens.HomescreenViewModel
import com.oleksiikravchuk.littlelemon.screens.OnboardingScreen
import com.oleksiikravchuk.littlelemon.screens.ProfileScreen
import com.oleksiikravchuk.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun NavigationComposable() {

    val navController = rememberNavController()

    val sharedPrefs = LocalContext.current.getSharedPreferences("Settings", Context.MODE_PRIVATE)

    val isLoggedIn = sharedPrefs.getBoolean("isLoggedIn", false)

    val applicationContext = LocalContext.current.applicationContext as MyApplication

    val homeScreenViewModel = remember {
        HomescreenViewModel(
            applicationContext.ktoClient,
            applicationContext.itemDao,
            applicationContext.getNetworkStatus()
        )
    }

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Home.route else Onboarding.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Onboarding.route) {
            OnboardingScreen(navController)
        }
        composable(Home.route) {
            HomeScreen(
                navController,
                homeScreenViewModel
            )
        }

        composable(Profile.route) {
            ProfileScreen(navController)
        }
    }


}