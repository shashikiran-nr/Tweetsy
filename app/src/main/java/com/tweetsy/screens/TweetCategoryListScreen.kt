package com.tweetsy.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tweetsy.R
import com.tweetsy.viewmodels.TweetCategoryViewModel
import java.util.Locale

@Composable
fun TweetCategoryScreen(onTweetClick: (category: String) -> Unit) {
    val tweetsCategoryViewModel: TweetCategoryViewModel = hiltViewModel()
    val categories by tweetsCategoryViewModel.categories.collectAsState()
    if (categories.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(1f)
        ) {
            Text(
                text = "Loading...",
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                color = Color.DarkGray,
            )
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            items(categories.distinct()) {
                TweetCategoryItem(category = it, onTweetClick)
            }
        }
    }
}

@Composable
fun TweetCategoryItem(category: String, onTweetClick: (category: String) -> Unit) {
    Card(modifier = Modifier
        .size(160.dp)
        .padding(8.dp)
        .clickable {
            onTweetClick(category)
        },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, Color(0xFFCCCCCC)),
        colors = CardDefaults.cardColors(Color.White),
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_category_bg),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
                Text(
                    text = category.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    color = Color.DarkGray,
                    modifier = Modifier.padding(8.dp)
                )
            }

        })
}