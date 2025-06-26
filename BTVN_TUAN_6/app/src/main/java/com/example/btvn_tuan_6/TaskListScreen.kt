package com.example.btvn_tuan_6

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TaskListScreen(navController: NavController, viewModel: TaskViewModel) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar()
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // Top Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* TODO: back */ }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Blue)
                }
                Text(
                    text = "List",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Blue
                )
                IconButton(onClick = {
                    navController.navigate(Screen.AddTask.route)
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.Red)
                }
            }

            // Task cards
            Column(modifier = Modifier.padding(16.dp)) {
                viewModel.tasks.forEachIndexed { index, task ->
                    val color = when (index % 3) {
                        0 -> Color(0xFFB3E5FC)
                        1 -> Color(0xFFF8BBD0)
                        else -> Color(0xFFC8E6C9)
                    }
                    TaskCard(task, color)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun TaskCard(task: Task, backgroundColor: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(
            text = task.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = task.description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun BottomNavigationBar() {
    BottomAppBar(
        containerColor = Color.White,
        tonalElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.Gray)
            Icon(Icons.Default.DateRange, contentDescription = "Calendar", tint = Color.Gray)
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFF2196F3), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Add, contentDescription = "Center Add", tint = Color.White)
            }
            Icon(Icons.Default.List, contentDescription = "List", tint = Color.Gray)
            Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.Gray)
        }
    }
}
