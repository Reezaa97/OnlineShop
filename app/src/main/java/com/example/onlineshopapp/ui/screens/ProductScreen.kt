package com.example.onlineshopapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp.ui.components.Loading
import com.example.onlineshopapp.ui.components.products.ProductItemView
import com.example.onlineshopapp.viewModels.product.ProductByCategoryViewModel

@Composable
fun ProductScreen(
    categoryId: Long,
    navController: NavHostController,
    viewModel: ProductByCategoryViewModel
    = hiltViewModel()
) {
    val animatedVisibleState =remember{
        MutableTransitionState(false).
    apply { targetState=true }
    }
    var dataList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember {
        mutableStateOf(viewModel.isLoading)

    }
    Column {


        LazyColumn {
            items(dataList.value.size) { index ->
                viewModel.onScrollPosition(index)
                if (index + 1 >= (viewModel.pageIndex.value * viewModel.pageSize)
                    && !viewModel.isLoading.value
                ) {
                    viewModel.nextPage()
                }
                androidx.compose.animation.AnimatedVisibility(visibleState = animatedVisibleState,
                    enter = slideInVertically(animationSpec = tween(500,1000),
                        initialOffsetY = { -40 }
                    ) +
                            fadeIn(
                                animationSpec = tween(500,1000)
                            ),
                    exit = fadeOut()
                ) {
                    ProductItemView(dataList.value[index], navController)
                }
                    Spacer(modifier = Modifier.height(10.dp))
                }
        }
        if (isLoading.value) {
            Loading(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp), 6
            )
        }
    }


}