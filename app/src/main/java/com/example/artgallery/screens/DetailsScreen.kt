package com.example.artgallery.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.artgallery.MainViewModel
import com.example.artgallery.utils.HtmlText

@Composable
fun DetailsScreen(viewModel: MainViewModel, itemId: String) {
    val currentItem = viewModel.allArts
        .observeAsState(listOf()).value
        .firstOrNull { it.id == itemId.toInt()}

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 8.dp)
    ) {
        LazyColumn {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Image(
                        painter = rememberAsyncImagePainter(currentItem?.image?.medium),
                        contentDescription = "",
                        modifier = Modifier.size(512.dp)
                    )
                    Text(
                        text = currentItem?.name?: "",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "Rating: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                        Text(
                            text = currentItem?.rating?.average.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                    }
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "Genre: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                        currentItem?.genres?.take(2)?.forEach {
                            Text(
                                text = "[$it] ",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                            )
                        }
                    }
                    HtmlText(
                        html = currentItem?.summary?: "",
                        modifier = Modifier
                            .padding(top = 10.dp)
                    )
                }
            }
        }
    }
}