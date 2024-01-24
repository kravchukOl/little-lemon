package com.oleksiikravchuk.littlelemon.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oleksiikravchuk.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun CategoryChip(title: String, onClick: () -> Unit) {


    var isPressed by remember {
        mutableStateOf(false)
    }


    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(size = 16.dp))
            .background(
                if (!isPressed)
                    MaterialTheme.colorScheme.inversePrimary
                else
                    MaterialTheme.colorScheme.primary
            )
            .clickable {
                isPressed = !isPressed
                onClick()
            }

    ) {
        Text(
            text = title,
            color =
            if (!isPressed)
                MaterialTheme.colorScheme.onPrimaryContainer
            else
                MaterialTheme.colorScheme.onPrimary,

            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

@Preview
@Composable

fun ChipPreview() {
    LittleLemonTheme {
        CategoryChip(title = "Desserts", onClick = {})
    }
}
