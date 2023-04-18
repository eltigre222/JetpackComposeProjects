package com.example.topappbar

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.topappbar.ui.theme.JetpackComposeProjectsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeProjectsTheme() {
                MainScreen(applicationContext)
            }
        }
    }
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(context: Context) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { host ->
            SnackbarHost(hostState = host){ data ->
                Snackbar(
                    backgroundColor = Color.White,
                    snackbarData = data,
                    shape = RoundedCornerShape(20.dp),
                    contentColor = Color.Red,
                    modifier = Modifier.padding(bottom = 50.dp)
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Menu")
                },
                backgroundColor = Color.White,
                navigationIcon = {
                    IconButton(
                        onClick = {
                            Toast.makeText(context, "Menu", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch{
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Item deleted!",
                                    actionLabel = "Undone"
                                )
                                if(result == SnackbarResult.ActionPerformed){
                                    Toast.makeText(context, "Item recovered", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete")
                    }
                    IconButton(
                        onClick = {
                            Toast.makeText(context, "Share", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Share")
                    }
                }
            )
        }
    ) {
    }
}