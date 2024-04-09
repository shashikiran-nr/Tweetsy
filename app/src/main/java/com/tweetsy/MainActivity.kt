package com.tweetsy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tweetsy.api.TweetsyAPI
import com.tweetsy.screens.TweetCategoryScreen
import com.tweetsy.screens.TweetListScreen
import com.tweetsy.ui.theme.TweetsyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TweetsyTheme {
                TweetsyApp()
            }
        }
    }
}

@Composable
fun TweetsyApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "category"
    ) {
        composable(route = "category") {
             TweetCategoryScreen{
                 navController.navigate("tweetlist/${it}")
             }
        }
        composable(
            route = "tweetlist/{category}",
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ) {
            TweetListScreen()
        }
    }
}



