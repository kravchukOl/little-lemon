package com.oleksiikravchuk.littlelemon.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oleksiikravchuk.littlelemon.R

@Composable
fun Header(modifier: Modifier = Modifier) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = R.drawable.little_lemon_logo),
            contentDescription = stringResource(R.string.lemon_logo_description),
            modifier = Modifier
                .padding(vertical = 24.dp)
                .width(140.dp)
        )

    }
}

@Preview
@Composable
fun HeaderPreview() {
    Header()
}