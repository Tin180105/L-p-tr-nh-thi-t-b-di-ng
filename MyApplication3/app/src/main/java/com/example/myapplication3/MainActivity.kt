package com.example.myapplication3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication3.ui.theme.MyApplication3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication3Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MainScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var hoTen by remember { mutableStateOf(TextFieldValue("")) }
    var tuoiText by remember { mutableStateOf(TextFieldValue("")) }
    var ketQua by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "THỰC HÀNH 1",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = hoTen,
            onValueChange = { hoTen = it },
            label = { Text("Nhập họ và tên") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = tuoiText,
            onValueChange = { tuoiText = it },
            label = { Text("Nhập tuổi") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(onClick = {
            val tuoi = tuoiText.text.toIntOrNull()
            ketQua = if (hoTen.text.isBlank() || tuoiText.text.isBlank()) {
                "Vui lòng nhập đầy đủ họ tên và tuổi!"
            } else if (tuoi == null) {
                "Tuổi phải là số hợp lệ!"
            } else {
                val loaiNguoi = when {
                    tuoi < 2 -> "Em bé"
                    tuoi < 6 -> "Trẻ em"
                    tuoi <= 65 -> "Người lớn"
                    else -> "Người già"
                }
                "Họ tên: ${hoTen.text}\nTuổi: $tuoi\nPhân loại: $loaiNguoi"
            }
        }) {
            Text("Kiểm Tra")
        }

        if (ketQua.isNotBlank()) {
            Text(text = ketQua)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MyApplication3Theme {
        MainScreen()
    }
}
