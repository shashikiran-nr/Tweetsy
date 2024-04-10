package com.tweetsy.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tweetsy.R
import com.tweetsy.viewmodels.TweetListViewModel
import java.util.Locale

@Composable
fun TweetListScreen() {
    val tweetListViewModel: TweetListViewModel = hiltViewModel()
    val tweets by tweetListViewModel.tweets.collectAsState()

    if (tweets.isEmpty()) {
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
        Column(Modifier.padding(8.dp)) {
            Text(
                text = tweetListViewModel.category!!.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                },
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(1f)
                    .background(
                        Color.LightGray,
                        RoundedCornerShape(4.dp)
                    )
                    .padding(8.dp)
            )
            LazyColumn(content = {
                items(tweets) {
                    TweetListItem(it.text)
                }
            })
        }
    }
}

@Composable
fun TweetListItem(tweet: String) {
    Card(modifier = Modifier
        .fillMaxWidth(1f)
        .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, Color(0xFFCCCCCC)),
        colors = CardDefaults.cardColors(Color.White),
        content = {
            Text(
                text = tweet,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                color = Color.DarkGray,
                modifier = Modifier.padding(16.dp)
            )
        })
}