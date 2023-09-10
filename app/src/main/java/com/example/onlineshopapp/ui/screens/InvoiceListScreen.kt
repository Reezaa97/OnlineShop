package com.example.onlineshopapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopapp.ui.components.invoices.InvoiceListItemView
import com.example.onlineshopapp.ui.components.Loading
import com.example.onlineshopapp.viewModels.invoice.InvoiceViewModel

@Composable
fun InvoiceListScreen(
    navController: NavController, viewModel: InvoiceViewModel = hiltViewModel()
) {
    var dataList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    LazyColumn {
        item {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")

            }
        }
            items(dataList.value.size) { index ->
                viewModel.onScrollPosition(index)
                if (index + 1 >= (viewModel.pageIndex.value * viewModel.pageSize)
                    && !viewModel.isLoading.value
                ) {
                    viewModel.nextPage()
                }
                InvoiceListItemView(dataList.value[index], navController)
                Spacer(modifier = Modifier.height(10.dp))


                if (isLoading.value) {
                    Loading(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp), 6
                    )
                }
            }
        }
    }

