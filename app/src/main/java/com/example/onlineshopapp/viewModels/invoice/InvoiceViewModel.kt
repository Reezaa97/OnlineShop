package com.example.onlineshopapp.viewModels.invoice

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.invoice.Invoice
import com.example.onlineshopapp.repositories.invoice.InvoiceRepository
import com.example.onlineshopapp.utils.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class InvoiceViewModel @Inject constructor(
    private val repository: InvoiceRepository
) : ViewModel() {

    var token: String = ThisApp.token
    var userId: Long = ThisApp.userId
    var pageSize = 3
    var pageIndex = mutableStateOf(0)
    private var scrollPosition = 0
    var dataList = mutableStateOf<List<Invoice>>(listOf())
    var isLoading = mutableStateOf(true)

    init {
        getInvoicesByUserId(userId, token, pageSize) { response ->
            isLoading.value=false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }

    fun addInvoices(
        data: Invoice, token: String, onResponse: (response: ServiceResponse<Invoice>)
        -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.addInvoice(data, token)
            onResponse(response)
        }
    }

    fun getInvoicesById(
        id: Long, token: String, onResponse: (response: ServiceResponse<Invoice>)
        -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.getInvoicesById(id, token)
            onResponse(response)
        }
    }


    fun getInvoicesByUserId(
        id: Long,
        token: String,
        pageIndex: Int,
        onResponse:
            (response: ServiceResponse<Invoice>) -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.getInvoiceByUserId(id, pageIndex, pageSize, token)
            onResponse(response)
        }
    }

    fun incrementPage() {
        pageIndex.value = pageIndex.value + 1
    }

    fun onScrollPosition(position: Int) {
        scrollPosition = position
    }

    fun appendItems(items: List<Invoice>) {
        var current = ArrayList(dataList.value)
        current.addAll(items)
        dataList.value = current
    }

    fun nextPage() {
        viewModelScope.launch {
            if ((scrollPosition + 1) >= (pageIndex.value * pageSize)) {
                isLoading.value = true
                incrementPage()

                if (pageIndex.value > 0) {
                    getInvoicesByUserId(
                        userId,
                        pageIndex.value.toString(),pageSize
                    ) { response ->
                        if (response.status == "OK" && response.data!!.isNotEmpty()) {
                            appendItems(response.data!!)
                        }
                        isLoading.value = false
                    }
                }
            }
        }
    }
}
