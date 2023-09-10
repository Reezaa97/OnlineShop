package com.example.onlineshopapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.onlineshopapp.db.viewmodels.BasketEntityViewModel
import com.example.onlineshopapp.ui.components.BasketItemView

@Composable
fun BasketListScreen(navController: NavHostController, basketViewModel: BasketEntityViewModel) {
    var dataList by remember { mutableStateOf(basketViewModel.dataList) }

    var totalPriceLong: Long = 0


    for (item in dataList.value) {
        totalPriceLong += item.quantity * item.price!!
    }

    var totalPrice = remember { mutableStateOf(totalPriceLong) }


    LazyColumn {
        item {
            Row {
                IconButton(onClick = { navController.popBackStack() }) {
                    androidx.compose.material.Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Shopping Cart", textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(10.dp))
                    if (dataList.value.isNotEmpty()) {
                        Text(
                            text = "${dataList.value.size} Items",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            color = Color.Gray
                        )


                    }

                }

            }
        }
        item {
            if (dataList.value.isEmpty()) {
                Column(
                    Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(text = "Your Shopping cart is empty", fontSize = 25.sp)
                    Spacer(modifier = Modifier.height(40.dp))
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart, contentDescription = "",
                        Modifier.size(250.dp), tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(20.dp))


                }


            }
        }

        items(dataList.value.size) { index ->

            BasketItemView(dataList.value[index], basketViewModel,totalPrice, navController)
        Spacer(modifier = Modifier.height(5.dp))
        }

        item {
            if (dataList.value.isNotEmpty()) {
                Spacer(modifier = Modifier.height(30.dp))
                Row {
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "total Price : ", fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${totalPrice.value}", fontSize = 24.sp,
                        fontWeight = FontWeight.Bold, color = Color.Gray
                    )

                }
                Spacer(modifier = Modifier.height(15.dp))
                Column(Modifier.padding(20.dp)) {


                    Button(
                        onClick = {
                            navController.navigate("proceedToPayment")
                        },
                        shape = RoundedCornerShape(20),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.DarkGray
                        )
                    ) {
                        Text(
                            text = " Proceed To Payment",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                    }
                }
            }
        }
    }
}


