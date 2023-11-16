package com.example.constraintlayoutlesson

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.constraintlayoutlesson.ui.theme.JetpackComposeProjectsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeProjectsTheme {
                GreetingPreview()
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (text, button, image) = createRefs()
        val bottomGuideline = createGuidelineFromBottom(0.2f )

        Button(onClick = {

        }, modifier = Modifier.constrainAs(button) {
            bottom.linkTo(bottomGuideline )
            end.linkTo(parent.end)
            start.linkTo(parent.start)
        }
        ) {
            Text(text = "Click me")
        }
        Text(text = "Hello Moto",
            modifier = Modifier.constrainAs(text) {
                bottom.linkTo(button.top, 16.dp)
                end.linkTo(button.end)
                start.linkTo(button.start)
            })
        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "image",
            modifier = Modifier.constrainAs(image) {
                bottom.linkTo(text.top, 16.dp)
                end.linkTo(button.end)
                start.linkTo(button.start)
            }
        )
    }
}