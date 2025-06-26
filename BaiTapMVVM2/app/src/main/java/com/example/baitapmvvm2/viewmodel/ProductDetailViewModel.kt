package com.example.baitapmvvm2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.baitapmvvm2.R
import com.example.baitapmvvm2.dataclass.Product

class ProductDetailViewModel : ViewModel() {
    fun getProductById(id: String): Product {
        return Product(
            id = "sp001",
            name = "Giày Nike Nam Nỉ Chính Hãng - Nike Air Force 1 '07 LV8 - Màu Trắng | JapanSport HF2998-100",
            price = "4.000.000đ",
            description = "Với giày này, sự tự tin, gu thời trang, độ êm ái và đẳng cấp sẽ đến cùng bạn. Đây là mẫu mã mới nhất của dòng Air Force đình đám từ Nike, phù hợp với mọi độ tuổi, phối đồ dễ dàng, đi học, đi làm hay đi chơi đều phù hợp.",
            imageResId = R.drawable.img // Bạn cần thêm ảnh này vào drawable
        )
    }
}