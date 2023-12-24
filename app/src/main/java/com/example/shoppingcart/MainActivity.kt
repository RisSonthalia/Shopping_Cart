package com.example.shoppingcart

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppingcart.ui.theme.ShoppingCartTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ShoppingCartViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingCartTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                     ShoppingApp(viewModel = viewModel)
                }
            }
        }
    }
}


fun getVegetablesList(): List<ShoppingCartItem> {
    return listOf(
        ShoppingCartItem("Carrot", 30f,R.drawable.carrot,"Carrot"),
        ShoppingCartItem("Cauliflower", 40f,R.drawable.cauliflower,"Cauliflower"),
        ShoppingCartItem("Cabbage", 50f,R.drawable.cabbage,"Cabbage"),
        ShoppingCartItem("Bringel", 15f,R.drawable.brinjal,"Bringel"),
        ShoppingCartItem("Potato", 15f,R.drawable.potato,"Potato"),
        ShoppingCartItem("Capsicum", 15f,R.drawable.capsicum,"Capsicum"),
        ShoppingCartItem("Green Chilli", 15f,R.drawable.greenchilli,"Green Chilli"),
        ShoppingCartItem("Ginger", 15f,R.drawable.onion,"Ginger"),
        ShoppingCartItem("Tomato", 15f,R.drawable.tomato,"Tomato"),
        ShoppingCartItem("LadyFinger", 15f,R.drawable.ladyfinger,"LadyFinger"),
    )
}

fun getFruitsList(): List<ShoppingCartItem> {
    return listOf(
        ShoppingCartItem("Mango", 100f, R.drawable.mango, "Mango"),
        ShoppingCartItem("Orange", 60f, R.drawable.orange, "Orange"),
        ShoppingCartItem("Pomegranate", 200f, R.drawable.pomegranate, "Pomegranate"),
        ShoppingCartItem("Dragon fruit", 500f, R.drawable.dragonfruit, "Dragon fruit"),
        ShoppingCartItem("Grapes", 60f, R.drawable.grapes, "Grapes"),
        ShoppingCartItem("Apple", 60f, R.drawable.apple, "Apple"),
        ShoppingCartItem("Banana", 60f, R.drawable.banana, "Banana"),
        ShoppingCartItem("Papaya", 60f, R.drawable.papaya, "Papaya"),
        ShoppingCartItem("Pineapple", 60f, R.drawable.pineapple, "Pineapple"),
        ShoppingCartItem("Kiwi", 60f, R.drawable.kiwi, "Kiwi"),
    )
}




