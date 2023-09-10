package com.example.onlineshopapp.ui.components.products

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onlineshopapp.models.product.Product
import com.example.onlineshopapp.viewModels.product.ProductViewModel

@Composable
fun ProductFilterView(viewModel: ProductViewModel = hiltViewModel()) {
    val filters = listOf("All", "New", "Popular")
    var selectedFilter by remember { mutableStateOf(0) }
    LazyRow {
        items(filters.size) { index ->
            TextButton(
                onClick = {
                    selectedFilter = index
                    when (selectedFilter) {
                        0 -> {
                            viewModel.getProducts(0, 6) {}
                        }

                        1 -> {
                            viewModel.getNewProducts {}
                        }

                        2 -> {
                            viewModel.getPopularProducts {}
                        }
                    }
                },

                colors = ButtonDefaults.buttonColors(

                    if (selectedFilter == index)
                        Color.LightGray else
                        Color.Transparent
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.width(90.dp)
            ) {
                Text(text = filters[index],color=if(isSystemInDarkTheme()&&selectedFilter !=index)
                    Color.White
                else Color.Black
                )


            }

            Spacer(modifier = Modifier.width(10.dp))

        }
    }
}