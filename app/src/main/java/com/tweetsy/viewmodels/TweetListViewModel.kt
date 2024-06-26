package com.tweetsy.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tweetsy.model.TweetListItem
import com.tweetsy.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetListViewModel @Inject constructor(
    private val repository: TweetRepository,
    private val savedStateHandle: SavedStateHandle) :
    ViewModel() {
        val tweets : StateFlow<List<TweetListItem>>
            get() = repository.tweets

    var category: String? = null

    init {
        viewModelScope.launch {
            category = savedStateHandle.get<String>("category") ?: "android"
            repository.getTweets(category!!)
        }
    }
}