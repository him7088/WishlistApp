package com.example.wishlistapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlistapp.WishViewModel
import com.example.wishlistapp.data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id : Long,
    viewModel: WishViewModel,
    navController: NavController,
    onBackNavClicked: () -> Unit
) {
    val snackMessage = remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember{SnackbarHostState()}
    val uiState = viewModel.uiState.collectAsState()

    if(id!= 0L)
    {
        val wish = viewModel.getWishById(id).collectAsState(initial = Wish(0L,"",""))
        viewModel.onWishDescriptionChange(wish.value.description)
        viewModel.onWishTitleChange(wish.value.title)
    }
    else {
        viewModel.onWishTitleChange("")
        viewModel.onWishDescriptionChange("")
    }
    Scaffold(
        topBar = {
            AppBarView(
                title =if (id!= 0L) "UpdateWish" else "Add Wish",
                onBackNavClicked = {
                onBackNavClicked()
                },
            navController
            )

        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize()
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = uiState.value.wishTitle,
                onValueChange = {newTitle->
                viewModel.onWishTitleChange(newTitle)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                label = { Text(text = "Title")},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                    ),


            )
            OutlinedTextField(
                value = uiState.value.wishDescription,
                onValueChange = {newDesc->
                    viewModel.onWishDescriptionChange(newDesc)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                label = { Text(text = "Description")},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                    ),
//                keyboardActions = KeyboardActions(onDone = {
//
//                })

            )
            Button(onClick = {
                if(uiState.value.wishTitle.isNotBlank() && uiState.value.wishDescription.isNotBlank()) {
                    //update
                    if(id!= 0L) {
                        viewModel.updateWish(
                            Wish(
                                id = id,
                                title = uiState.value.wishTitle.trim(),
                                description = uiState.value.wishDescription.trim()
                            )
                        )


                    }
                    //add
                    else {
                        viewModel.addWish(
                            Wish(
                                title = uiState.value.wishTitle.trim(),
                                description =uiState.value.wishDescription.trim()
                            )
                        )

                        scope.launch {
                            navController.navigateUp()
                        }
                    }
                }
                    else {
                    snackMessage.value ="Enter wish"
                    scope.launch { snackBarHostState.showSnackbar(snackMessage.value , duration = SnackbarDuration.Short) }
                    }

                }
            ) {
                Text(text = if (id!= 0L) "UpdateWish" else "Add Wish")
            }

        }
    }
}