package com.oleksiikravchuk.littlelemon.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.oleksiikravchuk.littlelemon.Profile
import com.oleksiikravchuk.littlelemon.components.Header

@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: HomescreenViewModel) {

    Column(Modifier.fillMaxSize()) {
        Header(Modifier.clickable { navHostController.navigate(Profile.route) })
    }

}