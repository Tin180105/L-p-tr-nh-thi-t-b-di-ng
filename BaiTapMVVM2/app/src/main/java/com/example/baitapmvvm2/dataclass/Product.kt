package com.example.baitapmvvm2.dataclass

import androidx.compose.ui.graphics.painter.Painter

// Dữ liệu sản phẩm
data class Product(
    val id: String,
    val name: String,
    val price: String,
    val description: String,
    val imageResId: Int
)