package com.example.school.screen.adminstrator

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.school.R
import kotlin.random.Random

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ClassWise() {
    // List of classes with random heights and colors
    val item = (1..18).map {
        ListItem(
            height = Random.nextInt(100, 300).dp,
            color = Color(Random.nextLong(0xFFFFFFFF)).copy(1f),
            className = getClassName(it)  // Set class name dynamically with suffix
        )
    }

    Box(modifier = Modifier.background(color = colorResource(id = R.color.red))) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp)
                .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .background(color = colorResource(id = R.color.white)),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalItemSpacing = 16.dp // Add vertical spacing between items
        ) {
            items(item) { listItem ->
                RandomColorBox(listItem)
            }
        }
    }
}

// Data class updated to include className
data class ListItem(val height: Dp, val color: Color, val className: String)

// Function to get class name with proper suffix
fun getClassName(classNumber: Int): String {
    return when (classNumber) {
        1 -> "Class 1st"
        2 -> "Class 2nd"
        3 -> "Class 3rd"
        in 4..10 -> "Class ${classNumber}th"
        11 -> "Class 11 Science (Medical)" // Class 11 Medical
        12 -> "Class 11 Science (Non-Medical)" // Class 11 Non-Medical
        13 -> "Class 11 Arts" // Class 11 Arts
        14 -> "Class 11 Commerce" // Class 11 Commerce
        15 -> "Class 12 Science (Medical)" // Class 12 Medical
        16 -> "Class 12 Science (Non-Medical)" // Class 12 Non-Medical
        17 -> "Class 12 Arts" // Class 12 Arts
        18 -> "Class 12 Commerce" // Class 12 Commerce
        else -> "Class ${classNumber}th"
    }
}

@Composable
fun RandomColorBox(item: ListItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(item.height)
            .clip(RoundedCornerShape(12.dp))
            .background(item.color)
    ) {
        // Display Drawable Image
        Image(
            painter = painterResource(id = R.drawable.school_logo),  // Use drawable here
            contentDescription = "School Logo",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
        )

        // Overlay Text
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )

        // Text with Class Name
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = item.className,  // Display class name
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

        }
    }
}
