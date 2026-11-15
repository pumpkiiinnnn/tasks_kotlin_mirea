package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

val primaryColor = Color(0xFF3F5F94)
val secondaryColor = Color(0xFF4A5568)
val buttonTextColor = Color.White

@Composable
fun WaterTracker(modifier: Modifier = Modifier) {
    var waterCount by remember { mutableIntStateOf(0) }
    var streakDays by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Трекер воды",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "$waterCount мл",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = secondaryColor
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            onClick = { waterCount += 250 }
        ) {
            Text(
                text = "+250 мл",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            onClick = {
                if (waterCount >= 1500) streakDays++ else streakDays = 0
                waterCount = 0
            }
        ) {
            Text(
                text = "Завершить день",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Дней подряд с 1500 мл и больше: $streakDays",
            fontSize = 18.sp,
            color = secondaryColor
        )
    }
}
