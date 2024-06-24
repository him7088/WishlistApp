package com.example.wishlistapp.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlistapp.R
import com.example.wishlistapp.WishViewModel
import com.example.wishlistapp.data.Wish

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    viewModel: WishViewModel,
    navController: NavController,
    title: String,
    onBackNavClicked : ()-> Unit) {
    Scaffold(
        topBar = {
            AppBarView(title = title, onBackNavClicked = onBackNavClicked,navController)

        },
        floatingActionButton = {
            FloatingActionButton(
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .padding(all = 20.dp),
                contentColor = Color.White,
                onClick = { navController.navigate(route = Screen.AddScreen.route + "/0L") },
                containerColor = colorResource(id = R.color.purple_200)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    //modifier = Modifier.background(colorResource(id = R.color.purple_200))
                )
            }
        }

    ) {
        val wishlist = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(wishlist.value, key = {wish -> wish.id}) {wish ->
                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = {swipeToDismissBoxValue ->
                        if(swipeToDismissBoxValue == SwipeToDismissBoxValue.StartToEnd || swipeToDismissBoxValue == SwipeToDismissBoxValue.EndToStart) {
                            viewModel.deleteWish(wish)
                        }
                        true
                    }
                )
                SwipeToDismissBox(
                    state = dismissState,
                    backgroundContent ={
                        val color by animateColorAsState(
                            if(dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart)
                            Color.Red
                            else {
                                Color.Transparent
                            }
                        )
                        val alignment = Alignment.CenterEnd
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .background(color = color),
                            contentAlignment = alignment
                        ){
                            Icon(
                                imageVector = Icons.Filled.Delete ,
                                contentDescription = "Delete icon",
                                tint = Color.White
                            )
                        }
                    } ,
                    enableDismissFromEndToStart = true,
                    enableDismissFromStartToEnd = false,
                    content = {

                        WishItem(wish = wish) {
                            val id = wish.id
                            navController.navigate(route = Screen.AddScreen.route + "/$id")
                        }
                    }
                )

                
            }
        }
    }
}

@Composable
fun WishItem(wish : Wish, onClick : () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Column (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()


        ){
            Text(text = wish.title , style = MaterialTheme.typography.bodyLarge)
            Text(text = wish.description,style = MaterialTheme.typography.bodyMedium )
        }
    }
}