package com.tweetsy.api

import com.tweetsy.model.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsyAPI {

    @GET("/v3/b/660f02dae41b4d34e4df4cb5?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String): Response<List<TweetListItem>>

    @GET("/v3/b/660f02dae41b4d34e4df4cb5?meta=false")
    @Headers("X-JSON-Path:tweets..category")
    suspend fun getCategory(): Response<List<String>>
}