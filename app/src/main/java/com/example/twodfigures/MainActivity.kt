package com.example.twodfigures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.dp
import kotlin.math.min

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
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White) {
        var offset by remember { mutableStateOf(0) }
        val animate = animateIntAsState( offset )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TwoDLine(animate.value)
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .scrollable(
                        orientation = Orientation.Horizontal,
                        // Scrollable state: describes how to consume
                        // scrolling delta and update offset
                        state = rememberScrollableState { delta ->
                            offset += delta.toInt()/10
                            delta
                        }
                    )
                    .background(Color.LightGray),
            contentAlignment = Alignment.Center
            ) {
                Text((offset/6.4).toInt().toString()+"%")
            }
        }
    }
}

@Composable
fun TwoDLine(scope:Int){
    Canvas(
        modifier = Modifier.size(500.dp,500.dp)
    ){
        val canvasWidth = size.width
        val canvasHeight = size.height

        for (i in 0..scope) {
            val multiplier = i/1f
            drawLine(
                start = Offset(canvasWidth/2,canvasHeight/2),
                end = Offset(min(0f + multiplier*2f,canvasWidth), canvasHeight) ,
                color = Color.Black,
                strokeWidth = 2f
            )
            drawLine(
                start = Offset(canvasWidth/2,canvasHeight/2),
                end = Offset(min(0f + multiplier*2f,canvasWidth), 0f) ,
                color = Color.Black,
            )
            drawLine(
                start = Offset(canvasWidth/2,canvasHeight/2),
                end = Offset(canvasWidth, min(0f + multiplier*2f,canvasHeight)) ,
                color = Color.Black,
            )
            drawLine(
                start = Offset(canvasWidth/2,canvasHeight/2),
                end = Offset(0f, min(0f + multiplier*2f,canvasHeight)) ,
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
@Composable
fun ScrollableSample() {
    // actual composable state
    var offset by remember { mutableStateOf(0f) }
    Box(
        Modifier
            .size(150.dp)
            .scrollable(
                orientation = Orientation.Horizontal,
                // Scrollable state: describes how to consume
                // scrolling delta and update offset
                state = rememberScrollableState { delta ->
                    offset += delta
                    delta
                }
            )
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(offset.toString())
    }
}
