package com.tweetsy.repository

import com.tweetsy.api.TweetsyAPI
import com.tweetsy.model.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/*
   TweetRepository: A repository class helps to fetch date from API class and pass to
   view model.
 */
class TweetRepository @Inject constructor(private val tweetsyAPI: TweetsyAPI) {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>>
        get() = _tweets

    suspend fun getCategories() {
        val response = tweetsyAPI.getCategory()
        if (response.isSuccessful && response.body() != null) {
            // emit data.
            _categories.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category: String) {
        val response = tweetsyAPI.getTweets("tweets[?(@.category==\"${category}\")]")
        if (response.isSuccessful && response.body() != null) {
            _tweets.emit(response.body()!!)
        }
    }
}

