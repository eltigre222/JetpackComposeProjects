package com.example.navigationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationcompose.ui.theme.JetpackComposeProjectsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController( )
            JetpackComposeProjectsTheme {
                NavHost(
                    navController = navController,
                    startDestination = "screen_1"
                ){
                    composable("screen_1") {
                        Screen1 {
                            navController.navigate("screen_2")
                        }
                    }
                    composable("screen_2") {
                        Screen2 {
                            navController.navigate("screen_3")
                        }
                    }
                    composable("screen_3") {
                        Screen3 {
                            navController.navigate("screen_1") {
                                popUpTo("screen_1") {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}