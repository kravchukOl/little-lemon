package com.oleksiikravchuk.littlelemon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.oleksiikravchuk.littlelemon.Profile
import com.oleksiikravchuk.littlelemon.R
import com.oleksiikravchuk.littlelemon.components.CategoryChip
import com.oleksiikravchuk.littlelemon.components.Header
import com.oleksiikravchuk.littlelemon.ui.theme.LittleLemonTheme
import com.oleksiikravchuk.littlelemon.ui.theme.darkGreen33
import com.oleksiikravchuk.littlelemon.ui.theme.lightYellow52
import com.oleksiikravchuk.littlelemon.ui.theme.primaryGreen

@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: HomescreenViewModel) {


    Surface(color = MaterialTheme.colorScheme.background) {
        Column(Modifier.fillMaxSize()) {

            Header(Modifier.clickable { navHostController.navigate(Profile.route) })

            MainBanner(
                searchPhrase = viewModel.searchPhrase.value,
                onSearchPhraseChanged = { viewModel.updateSearchPhrase(it) },
            )

            CategoryWidget { viewModel.filterByCategory(it) }

            HorizontalDivider(
                thickness = 2.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun MainBanner(
    searchPhrase: String, onSearchPhraseChanged: (String) -> Unit
) {
    Surface(color = darkGreen33) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp)
        ) {

            Text(
                text = stringResource(R.string.restaurant_name),
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold,
                color = lightYellow52

            )

            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.fillMaxWidth(0.6f)) {
                    Text(
                        text = stringResource(R.string.current_city),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    Text(
                        text = stringResource(R.string.banner_description),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(0.dp, 24.dp),
                        color = Color.White
                    )
                }
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Mediterranean dish",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        alignment = Alignment.Center,
                    )
                }
            }
            TextField(
                value = searchPhrase,
                onValueChange = onSearchPhraseChanged,
                singleLine = true,
                placeholder = {
                    Text(text = "Search for something new")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search")

                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

        }

    }
}

@Composable
fun CategoryWidget(onCategoryClicked: (String) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "ORDER FOR DELIVERY!",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CategoryChip(title = "Starters") { onCategoryClicked("Starters") }
            CategoryChip(title = "Mains") { onCategoryClicked("Mains") }
            CategoryChip(title = "Desserts") { onCategoryClicked("Desserts") }
            CategoryChip(title = "Drinks") { onCategoryClicked("Drinks") }

        }
    }
}

@Preview
@Composable
fun Preview() {
    LittleLemonTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(Modifier.fillMaxSize()) {
                MainBanner("", {})
                CategoryWidget({})
                HorizontalDivider(
                    thickness = 2.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}


