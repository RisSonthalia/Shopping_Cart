package com.example.shoppingcart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@Composable
fun ShoppingApp(viewModel: ShoppingCartViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Start.name
    ) {
        composable(Screen.Start.name) {
            StartScreen(navController, viewModel)
        }
        composable(Screen.Vegetables.name) {
            ItemListScreen(navController, viewModel, Screen.Vegetables, getVegetablesList())
        }
        composable(Screen.Fruits.name) {
            ItemListScreen(navController, viewModel, Screen.Fruits, getFruitsList())
        }
        composable(Screen.Bill.name) {
            BillScreen(navController, viewModel)
        }
    }
}

@Composable
fun StartScreen(navController: NavController, viewModel: ShoppingCartViewModel) {

    val annotatedString = buildAnnotatedString {
        append("Welcome to \n")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp,)) {
            append("The Shopping Cart App !! ")
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(90.dp))
        Image(painter = painterResource(id = R.drawable.basket),
            contentDescription = "basket_image",
            modifier = Modifier
                .fillMaxWidth()
                .size(150.dp)
                .rotate(-30f)
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),

            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(100.dp))

        Text(text=annotatedString,
            modifier= Modifier
                .padding(16.dp)
                .fillMaxWidth()
            ,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row (modifier = Modifier
            .fillMaxSize(),
            horizontalArrangement = Arrangement.Center

        ){
            Button(onClick = { navController.navigate(Screen.Vegetables.name) },
            ) {
                Text("Go to Vegetables")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {   navController.navigate(Screen.Fruits.name) }) {
                Text("Go to Fruits")
            }
        }

    }

}

@Composable
fun ItemListScreen(
    navController: NavController,
    viewModel: ShoppingCartViewModel,
    screen: Screen,
    itemList: List<ShoppingCartItem>
) {
    Scaffold(
        topBar = {
            AppToolBar()
        }
    ) { paddingValues ->
        Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(paddingValues)
        ) {


            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp),

                ) {
                item {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ){

                        Button(onClick = { navController.navigate(Screen.Start.name)  }) {
                            Text(
                                text = "Go to Main menu",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp),
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                    Text(text = "\n")
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp)
                    ){
                        Button(onClick = { navController.navigate(Screen.Fruits.name) }) {
                            Text( text = "Go to Fruits",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp),
                                textAlign = TextAlign.Center)
                        }

                        Button(onClick = { navController.navigate(Screen.Vegetables.name) }) {
                            Text( text = "Go to Vegetables",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp),
                                textAlign = TextAlign.Center)
                        }
                    }
                }
                itemsIndexed(itemList) { index, item ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),

                        ) {
                        Text(
                            text = "${(index + 1).toString()}. ${item.name} \n",
                            modifier = Modifier.padding(10.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Image(
                            painter = painterResource(id = item.imageResourceId),
                            contentDescription = item.ContentDescription,
                            modifier = Modifier.size(100.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Rs.${item.price} per kg",
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        if (viewModel.getcount(item) == 0) {
                            Button(onClick = {
                                viewModel.addItem(item)
                            }) {
                                Text("Add to Cart")
                            }
                        } else {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(2.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(35.dp)
                                        .clip(CircleShape)
                                        .background(color = Color.Transparent)
                                        .border(
                                            1.dp, Color.Black,
                                            CircleShape
                                        )
                                ) {

                                    IconButton(
                                        onClick = {
                                            viewModel.removeItem(item)
                                                  },
                                        ) {
                                        Icon(
                                            Icons.Default.Close,
                                            contentDescription = stringResource(R.string.remove)
                                        )
                                    }
                                }

                                Text(
                                    text ="${viewModel.getcount(item).toString()} Kg",
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )

                                Box(
                                    modifier = Modifier
                                        .size(35.dp)
                                        .clip(CircleShape)
                                        .background(color = Color.Transparent)
                                        .border(
                                            1.dp, Color.Black,
                                            CircleShape
                                        )
                                ) {
                                    IconButton(
                                        onClick = {

                                            viewModel.addItem(item)
                                        }
                                    ) {
                                        Icon(
                                            Icons.Default.Add,
                                            contentDescription = stringResource(R.string.add)
                                        )
                                    }
                                }
                            }
                        }
                        if(viewModel.getcount(item) !=0) {
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedButton(onClick = { navController.navigate(Screen.Bill.name) }) {
                                Text("Go to Bill")
                            }
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                    }
                }
            }
        }
    }
}
@Composable
fun BillScreen(navController: NavController, viewModel: ShoppingCartViewModel) {
    Scaffold(
        topBar = {
            AppToolBar()
        }
    ) { paddingValues ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(paddingValues)
        ) {


            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(10.dp),

                ) {
                item {

                    Column {

                        Button(onClick = { navController.navigate(Screen.Start.name)  }) {
                            Text(
                                text = "Go to Main menu",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp),
                                textAlign = TextAlign.Center
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Button(onClick = { navController.navigate(Screen.Fruits.name)  }) {
                                Text(text = "Go to Fruits")
                            }
                            Spacer(modifier = Modifier.width(10.dp))

                            Button(onClick = { navController.navigate(Screen.Vegetables.name)  }) {
                                Text(text = "Go to Vegetables")
                            }

                        }
                    }


                    Spacer(modifier = Modifier.height(16.dp))
                }

                itemsIndexed(viewModel.itemList.distinctBy { it.name }) { index, item ->

                        Box(
                            modifier = Modifier

                                .border(
                                    width = 1.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(45.dp)
                                )
                                .padding(10.dp)

                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(1.dp),

                                ) {
                                Text(
                                    text = "${(index + 1).toString()}.",
                                    fontSize = 20.sp
                                )
                                Spacer(modifier = Modifier.width(1.dp))
                                Box(
                                    modifier = Modifier
                                        .padding(vertical = 20.dp)

                                ) {
                                    Image(
                                        painter = painterResource(id = item.imageResourceId),
                                        contentDescription = item.ContentDescription,
                                        modifier = Modifier.size(55.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "${item.name}",
                                        modifier = Modifier.padding(10.dp),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                    Text(
                                        "Rs.${item.price} per kg",
                                    )
                                }

                                Spacer(modifier = Modifier.width(10.dp))

                                    Box(
                                        modifier = Modifier
                                            .padding(vertical = 30.dp)
                                    ) {
                                        Row {

                                            Box(
                                                modifier = Modifier
                                                    .size(22.dp)
                                                    .clip(CircleShape)
                                                    .background(color = Color.Transparent)
                                                    .border(
                                                        1.dp,
                                                        Color.Black,
                                                        CircleShape
                                                    )
                                            ) {
                                                IconButton(
                                                    onClick = {
                                                        viewModel.removeItem(item)
                                                              },
                                                    ) {
                                                    Icon(
                                                        Icons.Default.Clear,
                                                        contentDescription = stringResource(R.string.remove)
                                                    )
                                                }
                                            }

                                            Text(
                                                text = "${viewModel.getcount(item).toString()} Kg",
                                            )

                                            Box(
                                                modifier = Modifier
                                                    .size(22.dp)
                                                    .clip(CircleShape)
                                                    .background(color = Color.Transparent)
                                                    .border(
                                                        1.dp,
                                                        Color.Black,
                                                        CircleShape
                                                    )
                                            ) {
                                                IconButton(
                                                    onClick = {
                                                        viewModel.addItem(item)
                                                    }
                                                ) {
                                                    Icon(
                                                        Icons.Default.Add,
                                                        contentDescription = stringResource(R.string.add)
                                                    )
                                                }
                                            }

                                        }
                                    }

                            }


                        }
                        Spacer(modifier = Modifier.height(10.dp))

                }

                item {

                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color.Gray,
                                shape = RoundedCornerShape(200.dp)
                            )
                            .height(100.dp)
                            .padding(30.dp)

                    ) {

                        val price =
                            Text(
                                "Grand Total : Rs.${viewModel.itemList.sumByDouble { it.price.toDouble() }}",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Black
                            )
                    }
                }

                item {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp),
                        Alignment.Center

                    ) {
                        Button(onClick = {
                            viewModel.clearItemList()
                            navController.navigate(Screen.Start.name)
                        }) {
                            Text("Finish Shopping")
                        }
                    }

                }


            }

        }
    }
}

