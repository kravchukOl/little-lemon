package com.oleksiikravchuk.littlelemon.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.oleksiikravchuk.littlelemon.MenuItem
import com.oleksiikravchuk.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun DishItem(item: MenuItem) {

    Column(Modifier.fillMaxWidth()) {
        Text(
            text = item.title,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onPrimary
        )

    }
}

@Preview
@Composable
fun DishItemPreview() {
    LittleLemonTheme {
        DishItem(
            MenuItem(
                id = 1,
                title = "Greek Salad",
                description = "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
                price = "10",
                image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
                category = "starters"
            )
        )
    }
}