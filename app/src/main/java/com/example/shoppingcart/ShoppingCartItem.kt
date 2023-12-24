package com.example.shoppingcart

data class ShoppingCartItem(
    val name: String,
    val price: Float,
    val imageResourceId:Int,
    val ContentDescription : String,
    var count:Int=1
)
