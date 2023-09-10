package com.example.onlineshopapp.ui.components.invoices

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.onlineshopapp.models.invoice.Invoice
import com.example.onlineshopapp.ui.components.AdvancedButton

@Composable
fun InvoiceListItemView(invoice: Invoice, navController: NavController) {
    AdvancedButton(invoice.addData!!,invoice.status!!,
        if (invoice.paymentData!!.isEmpty())Icons.Filled.Close else
            Icons.Filled.Check,
        if (invoice.paymentData!!.isEmpty()) Color.Red else
            Color.Green,            ){
        navController.navigate("invoices/${invoice.id}")
}

}