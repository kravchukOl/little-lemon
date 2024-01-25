package com.oleksiikravchuk.littlelemon.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.oleksiikravchuk.littlelemon.MenuItem
import com.oleksiikravchuk.littlelemon.ui.theme.LittleLemonTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DishItem(item: MenuItem) {

    Surface(
        color = MaterialTheme.colorScheme.background,
    ) {
        HorizontalDivider(
            thickness = 2.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = item.title,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Row(Modifier.fillMaxWidth()) {
                Column(Modifier.fillMaxWidth(0.7f)) {
                    Text(
                        text = item.description,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "$${item.price.toString()}.00",
                        fontWeight = FontWeight.SemiBold
                    )
                }
                GlideImage(
                    model = item.image,
                    contentDescription = "${item.title} image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Gray)
                )
            }

        }
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