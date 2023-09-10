package com.example.onlineshopapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlineshopapp.R
import com.example.onlineshopapp.db.model.BasketEntity
import com.example.onlineshopapp.db.viewmodels.BasketEntityViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BasketItemView(
    basketEntity: BasketEntity, viewModel: BasketEntityViewModel,
    totalPrice: MutableState<Long>,
    navController: NavController
) {
    var quantity by remember { mutableStateOf(basketEntity.quantity) }
    Row(Modifier.fillMaxWidth().padding(20.dp,0.dp)) {
        Card(
            modifier = Modifier.size(100.dp),
            shape = RoundedCornerShape(20.dp),
            onClick = { navController.navigate(
                "showProduct/${basketEntity.productId}") }
        )

        {
            GlideImage(
                imageModel = { basketEntity.image!! },
                modifier = Modifier.fillMaxSize(),
                loading = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(30.dp),


                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))

                        CircularProgressIndicator()
                    }
                },
                // shows an error text message when request failed.
                failure = {
                    Text(text = "image request failed.")
                })
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = basketEntity.title!!, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "${basketEntity.price!!}T")
            Spacer(modifier = Modifier.height(5.dp))

Row {

    Spacer(modifier = Modifier.height(5.dp))

    Card(


        shape = RoundedCornerShape(50.dp),
        backgroundColor =
        Color(android.graphics.Color.parseColor("#${basketEntity.colorHex}")),

        modifier = Modifier.size(40.dp),

        border = if (basketEntity.colorHex == "FFFFFF") {
            BorderStroke(1.dp, androidx.compose.ui.graphics.Color.Black)
        } else {
            BorderStroke(1.dp, androidx.compose.ui.graphics.Color.White)
        },
        content = {}


    )
    Spacer(modifier = Modifier.height(5.dp))

    Text(text = basketEntity.size, fontSize = 22.sp, color = Color.Gray)

}



        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            IconButton(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.decrementQuantity(basketEntity)
                }
                quantity--
                totalPrice.value -=basketEntity.price!!
            }) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_remove_circle_24),
                    contentDescription = ""
                )
            }
            Text(text = quantity.toString())
            IconButton(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.incrementQuantity(basketEntity)
                }
                quantity++
                totalPrice.value +=basketEntity.price!!

            }) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_add_circle_24),
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.height(30.dp))

            IconButton(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.delete(basketEntity)
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "", tint = Color.Red
                )
            }
        }
    }
}