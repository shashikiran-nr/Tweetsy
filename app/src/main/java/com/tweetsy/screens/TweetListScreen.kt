package com.tweetsy.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tweetsy.model.TweetListItem
import com.tweetsy.viewmodels.TweetListViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun TweetListScreen() {
    val tweetListViewModel: TweetListViewModel = hiltViewModel()
    val tweets by tweetListViewModel.tweets.collectAsState()

    LazyColumn(content = {
        items(tweets){
            TweetListItem(it.text)
        }
    })

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
            Text(text = tweet,
                modifier = Modifier.padding(16.dp),
                color = Color.DarkGray,
                style = MaterialTheme.typography.bodyMedium)
        })
}