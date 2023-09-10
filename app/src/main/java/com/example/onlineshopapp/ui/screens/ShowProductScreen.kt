package com.example.onlineshopapp.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.example.onlineshopapp.db.model.BasketEntity
import com.example.onlineshopapp.db.viewmodels.BasketEntityViewModel
import com.example.onlineshopapp.ui.components.Loading
import com.example.onlineshopapp.viewModels.product.ProductViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ShowProductScreen(
    productId: Long,
    navController: NavHostController,
    basketViewModel: BasketEntityViewModel,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val animatedVisibleState =remember{
        MutableTransitionState(false).
    apply { targetState=true }
    }
    var data by remember { mutableStateOf(viewModel.data) }
    var isLoading by remember { mutableStateOf(true) }
    var selectedSize by remember { mutableStateOf(0) }
    var selectedColor by remember { mutableStateOf(0) }

    var context = LocalContext.current

    viewModel.getProductById(productId) { response ->
        isLoading = false
        if (response.status == "OK") {
            if (response.data!!.isNotEmpty()) {
                viewModel.data.value = response.data!![0]
            } else {
                Toast.makeText(
                    context, "Error on load Data!",
                    Toast.LENGTH_LONG
                ).show()
                navController.popBackStack()

            }
        }
    }
    if (isLoading) {
        Loading(
            modifier = Modifier.fillMaxSize()
        )
    } else {
        Card(
            modifier = Modifier
                .fillMaxSize(),

            shape = RoundedCornerShape(0.dp),

            ) {
            Box {
                GlideImage(
                    imageModel = { data.value!!.image!! },
                    modifier = Modifier.fillMaxSize(),
                    loading = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(15.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    },
                    // shows an error text message when request failed.
                    failure = {
                        Text(text = "image request failed.")
                    })
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black)

                        )
                    )
            ) {
                {

                }

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column() {
                    androidx.compose.animation.AnimatedVisibility(visibleState = animatedVisibleState,
                        enter = slideInVertically(animationSpec = tween(500,500),
                            initialOffsetY = { -40 }
                        ) +
                                fadeIn(
                                    animationSpec = tween(500,500)
                                ),
                        exit = fadeOut()
                    ) {
                        Text(
                            text = data.value!!.title!!,
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )}
                        Spacer(modifier = Modifier.height(10.dp))

                    AnimatedVisibility(visibleState = animatedVisibleState,
                        enter = slideInVertically (animationSpec = tween(500,1000),
                            initialOffsetY = {-40}
                        )+
                                fadeIn(
                                    animationSpec = tween(500,1000)
                                ),
                        exit = fadeOut()
                    ) {
                        Text(
                            text = "${data.value!!.price!!}T",
                            color = Color.White,
                            fontSize = 26.sp
                        )}
                        Spacer(modifier = Modifier.height(10.dp))
                    AnimatedVisibility(visibleState = animatedVisibleState,
                        enter = slideInVertically (animationSpec = tween(500,1500),
                            initialOffsetY = {-40}
                        )+
                                fadeIn(
                                    animationSpec = tween(500,1500)
                                ),
                        exit = fadeOut()
                    ) {
                        Text(
                            text = "Sizes",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )}
                        Spacer(modifier = Modifier.height(10.dp))
                    AnimatedVisibility(visibleState = animatedVisibleState,
                        enter = slideInVertically (animationSpec = tween(500,1800),
                            initialOffsetY = {-40}
                        )+
                                fadeIn(
                                    animationSpec = tween(500,1800)
                                ),
                        exit = fadeOut()
                    ) {
                        LazyRow {
                            items(data.value!!.sizes!!.size) { index ->
                                androidx.compose.material.TextButton(
                                    onClick = { selectedSize = index },
                                    shape = RoundedCornerShape(15.dp),
                                    colors = androidx.compose.material.ButtonDefaults.buttonColors(
                                        backgroundColor =
                                        if (selectedSize == index) Color.White else Color.Transparent
                                    ),
                                    modifier = Modifier.size(40.dp)
                                ) {
                                    Text(
                                        text = data.value!!.sizes!![index].title!!,
                                        fontWeight = FontWeight.Bold,
                                        color = if (selectedSize == index) Color.Black else Color.White
                                    )
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                            }
                        }}
                        Spacer(modifier = Modifier.height(10.dp))
                    AnimatedVisibility(visibleState = animatedVisibleState,
                        enter = slideInVertically (animationSpec = tween(500,2300),
                            initialOffsetY = {-40}
                        )+
                                fadeIn(
                                    animationSpec = tween(500,2300)
                                ),
                        exit = fadeOut()
                    ) {
                        Text(
                            text = "Color",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )}
                        Spacer(modifier = Modifier.height(10.dp))
                    AnimatedVisibility(visibleState = animatedVisibleState,
                        enter = slideInVertically (animationSpec = tween(500,2800),
                            initialOffsetY = {-40}
                        )+
                                fadeIn(
                                    animationSpec = tween(500,2800)
                                ),
                        exit = fadeOut()
                    ) {
                        LazyRow {
                            items(data.value!!.colors!!.size) { index ->
                                androidx.compose.material.TextButton(
                                    onClick = { selectedColor = index },
                                    shape = RoundedCornerShape(50.dp),
                                    colors = androidx.compose.material.ButtonDefaults.buttonColors(
                                        backgroundColor =
                                        Color(
                                            android.graphics.Color.parseColor
                                                ("#${data.value!!.colors!![index].hexValue}")
                                        )

                                    ),
                                    modifier = Modifier.size(40.dp),
                                    border = BorderStroke(1.dp, Color.White)

                                ) {
                                    if (selectedColor == index) {
                                        Icon(
                                            imageVector = Icons.Filled.Check,
                                            contentDescription = "",
                                            tint = if (data.value!!.colors!![index].hexValue == "FFFFFF")
                                                Color.Black else Color.White
                                        )
                                    }

                                }
                                Spacer(modifier = Modifier.height(5.dp))
                            }
                        }
                    }
                        Spacer(modifier = Modifier.height(40.dp))
                    AnimatedVisibility(visibleState = animatedVisibleState,
                        enter = slideInVertically (animationSpec = tween(500,3300),
                            initialOffsetY = {-40}
                        )+
                                fadeIn(
                                    animationSpec = tween(500,3300)
                                ),
                        exit = fadeOut()
                    ) {
                        androidx.compose.material.TextButton(
                            onClick = {
                                CoroutineScope(Dispatchers.IO).launch {
                                    val basket = BasketEntity(
                                        productId = productId,
                                        quantity = 1,
                                        sizeId = data.value!!.sizes!![selectedSize].id!!,
                                        colorId = data.value!!.colors!![selectedColor].id!!,
                                        image = data.value!!.image,
                                        price = data.value!!.price,
                                        title = data.value!!.title,
                                        colorHex = data.value!!.colors!![selectedColor].hexValue!!,
                                        size = data.value!!.sizes?.get(selectedSize)!!.title!!
                                    )
                                    basketViewModel.addToBasket(basket)

                                }
                                Toast.makeText(
                                    context,
                                    "Product add to your Basket",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                                navController.popBackStack()
                            },
                            shape = RoundedCornerShape(15.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = androidx.compose.material.ButtonDefaults.buttonColors(
                                backgroundColor = Color.White
                            )

                        ) {

                            Text(
                                text = "Buy Now ", fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )

                        }
                    }
                        Spacer(modifier = Modifier.height(40.dp))

                    }
            }

        }
    }
}


