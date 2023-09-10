package com.example.onlineshopapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopapp.ui.components.Loading
import com.example.onlineshopapp.viewModels.invoice.InvoiceItemViewModel

@Composable
fun InvoiceScreen(
    navController: NavController,
    id: Long,
    viewModel: InvoiceItemViewModel = hiltViewModel()
) {
    var invoice by remember { mutableStateOf(viewModel.data) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    if (isLoading.value) {
        Loading(modifier = Modifier.fillMaxSize())
    } else {


        Column(Modifier.padding(20.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.padding(5.dp),
                    shape = RoundedCornerShape(50.dp),
                    backgroundColor = if (invoice.value!!.paymentData!!.isEmpty()) Color.Red else
                        Color.Green,

                    ) {


                    Icon(
                        imageVector = if (invoice.value!!.paymentData!!.isEmpty()) Icons.Filled.Close else
                            Icons.Filled.Check, contentDescription = "",
                        Modifier
                            .size(250.dp)
                            .padding(30.dp), tint = Color.Red
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                }
            }
        }
    }
}