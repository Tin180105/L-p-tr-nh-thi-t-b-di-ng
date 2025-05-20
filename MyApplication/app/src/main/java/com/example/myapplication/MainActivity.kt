package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InputScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun InputScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var age by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var message by remember { mutableStateOf("") }
    var messageColor by remember { mutableStateOf(Color.Red) }

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Thực hành 02", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Tên") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Tuổi") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = message,
            color = messageColor
        )

        Button(
            onClick = {
                val emailText = email.text.trim()
                if (emailText.isEmpty()) {
                    message = "Email không hợp lệ"
                    messageColor = Color.Red
                } else if (!emailText.contains("@")) {
                    message = "Email không đúng định dạng"
                    messageColor = Color.Red
                } else {
                    message = "Bạn đã nhập email hợp lệ"
                    messageColor = Color.Green
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kiểm tra")
        }
    }
}
