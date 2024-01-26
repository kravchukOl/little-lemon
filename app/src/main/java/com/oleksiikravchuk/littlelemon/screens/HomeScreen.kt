package com.oleksiikravchuk.littlelemon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.oleksiikravchuk.littlelemon.MenuItem
import com.oleksiikravchuk.littlelemon.Profile
import com.oleksiikravchuk.littlelemon.R
import com.oleksiikravchuk.littlelemon.components.CategoryChip
import com.oleksiikravchuk.littlelemon.components.CategoryState
import com.oleksiikravchuk.littlelemon.components.DishItem
import com.oleksiikravchuk.littlelemon.components.Header
import com.oleksiikravchuk.littlelemon.ui.theme.LittleLemonTheme
import com.oleksiikravchuk.littlelemon.ui.theme.darkGreen33
import com.oleksiikravchuk.littlelemon.ui.theme.lightYellow52

@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: HomescreenViewModel) {

    val categoryUiState = viewModel.categoryListState

    Surface(color = MaterialTheme.colorScheme.background) {
        Column(Modifier.fillMaxSize()) {

            Header(Modifier.clickable { navHostController.navigate(Profile.route) })
            MainBanner(
                searchPhrase = viewModel.searchPhrase.value,
                onSearchPhraseChanged = {
                    viewModel.updateSearchPhrase(it)
                },
            )
            CategoryWidget(categoryUiState) { viewModel.filterByCategory(it) }
            MenuItems(itemsList = viewModel.listOfMenuItemState.value)

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
                        fontSize = 15.sp,
                        modifier = Modifier.padding(0.dp, 16.dp),
                        color = Color.White
                    )
                }
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Mediterranean dish",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .size(120.dp)
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
                    Text(text = stringResource(R.string.search_placeholder))
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = "")

                },
                modifier = Modifier.fillMaxWidth()
            )

        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryWidget(
    categories: List<CategoryState>, onCategoryClicked: (String) -> Unit
) {
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

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            categories.forEach {
                CategoryChip(it.categoryName, it.isActive) {
                    onCategoryClicked(it.categoryName)
                }
            }
        }
    }
}

@Composable
fun MenuItems(itemsList: List<MenuItem>) {
    LazyColumn {
        items(itemsList) { item ->
            DishItem(item = item)
        }
    }
}

@Preview
@Composable
fun Preview() {
    LittleLemonTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(Modifier.fillMaxSize()) {
                MainBanner("") {}
                CategoryWidget(
                    listOf(
                        CategoryState("Starters"),
                        CategoryState("Mains"),
                        CategoryState("Desserts"),
                        CategoryState("Drinks"),
                    )
                ) {}
            }
        }
    }
}


