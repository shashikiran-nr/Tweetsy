package com.tweetsy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tweetsy.screens.TweetCategoryScreen
import com.tweetsy.screens.TweetListScreen
import com.tweetsy.ui.theme.TweetsyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TweetsyTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = stringResource(id = R.string.app_name),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily(Font(R.font.montserrat_regular))
                                )
                            }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = Color.DarkGray,
                                titleContentColor = Color.LightGray
                            )
                        )
                    }
                ) {
                    Surface(modifier = Modifier
                        .padding(it)
                        .background(Color.White)) {
                        TweetsyApp()
                    }
                }
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
            TweetCategoryScreen {
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



