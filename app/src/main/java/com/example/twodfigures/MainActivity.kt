package com.example.twodfigures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.twodfigures.ui.theme.TwoDFiguresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwoDLineWithScope()
        }
    }
}

@Composable
fun TwoDLineWithScope(){
    Surface(modifier = Modifier.fillMaxSize()) {
        var clicked by remember{ mutableStateOf(false)}
        val animate = animateIntAsState(if (clicked) 11 else 0)
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            TwoDLine(animate.value)
            Button(onClick = { clicked = !clicked}, modifier = Modifier.padding(10.dp)) {
                Text(text = "Try This One")
            }

        }

    }
}

@Composable
fun TwoDLine(scope:Int){
    Canvas(modifier = Modifier.size(500.dp,500.dp)){
        val canvasWidth = size.width
        val canvasHeight = size.height

        for (i in 0..scope) {
            val multiplier = i/1f
            drawLine(
                start = Offset(canvasWidth/2,canvasHeight/2),
                end = Offset(0f + multiplier*100f, canvasHeight) ,
                color = Color.Black,
            )

        }
    }
}

@Composable
fun TwoDCircle(){
    Canvas(modifier = Modifier.fillMaxSize()){
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawCircle(
            radius = size.minDimension/4,
            center = Offset(canvasWidth/2,canvasHeight/2),
            color = Color.Black,
        )
    }
}

@Composable
fun TwoDRect(){
    Canvas(modifier = Modifier.fillMaxSize()){
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawRect(
            topLeft = Offset(canvasWidth/2,canvasHeight/2),
            size = size/3f,
            color = Color.Black,
        )
    }
}


@Composable
fun Figure(){
    Canvas(modifier = Modifier.fillMaxSize()){
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawPoints(
            points = listOf(
                Offset(canvasWidth/2,canvasHeight/2),
                Offset(canvasWidth/1,canvasHeight/1),
                Offset(canvasWidth/2+canvasWidth/4,canvasHeight/2),
                Offset(canvasWidth/2,canvasHeight/2),

                ),
            pointMode = PointMode.Polygon,
            color = Color.Black,
        )
    }
}
