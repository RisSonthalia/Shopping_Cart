package com.example.shoppingcart

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ShoppingCartViewModel : ViewModel() {
    private val _itemList = mutableStateListOf<ShoppingCartItem>()
    val itemList: List<ShoppingCartItem> get() = _itemList

    private val _currentScreen = mutableStateOf(Screen.Start)
    val currentScreen: Screen get() = _currentScreen.value

    fun addItem(item: ShoppingCartItem) {
        _itemList.add(item)
    }

    fun removeItem(item: ShoppingCartItem) {
        _itemList.remove(item)
    }

    fun getcount(item:ShoppingCartItem):Int{
        return _itemList.filter { it.name == item.name }.sumBy { it.count }
    }

    fun getTotalPrice(): Float {
        return _itemList.sumByDouble { it.price.toDouble() * it.count.toDouble() }.toFloat()
    }

    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }

    fun clearItemList() {
        _itemList.clear()
    }
}
