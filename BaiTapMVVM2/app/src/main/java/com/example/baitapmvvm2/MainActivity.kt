package com.example.baitapmvvm2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.baitapmvvm2.view.ProductDetailScreen
import com.example.baitapmvvm2.viewmodel.ProductDetailViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = ProductDetailViewModel()
            ProductDetailScreen(
                viewModel = viewModel,
                productId = "sp001",
                onBackClick = { finish() } // <- Sửa tại đây
            )
        }
    }
}
