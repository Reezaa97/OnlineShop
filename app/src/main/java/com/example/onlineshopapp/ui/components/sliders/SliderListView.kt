package com.example.onlineshopapp.ui.components.sliders

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onlineshopapp.ui.components.Loading
import com.example.onlineshopapp.viewModels.site.SliderViewModel

@Composable
fun SliderListView(viewModel: SliderViewModel = hiltViewModel()) {
    var dataList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    if (isLoading.value) {
       Loading(modifier = Modifier.width(300.dp).height(200.dp),2)
    } else {

        LazyRow {
            items(dataList.value.size) { index ->
                SliderItemView(dataList.value[index])
                Spacer(modifier = Modifier.width(10.dp))

            }
        }
    }
}