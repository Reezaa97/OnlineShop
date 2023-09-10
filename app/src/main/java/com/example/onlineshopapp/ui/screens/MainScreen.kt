package com.example.onlineshopapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.onlineshopapp.MainActivity
import com.example.onlineshopapp.db.viewmodels.BasketEntityViewModel
import com.example.onlineshopapp.db.viewmodels.UserEntityViewModel
import com.example.onlineshopapp.ui.components.TopAppView
import com.example.onlineshopapp.utils.ThisApp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun MainScreen(mainActivity: MainActivity) {
    val navController = rememberNavController()
    var fullScreen by remember { mutableStateOf(false) }
    val basketViewModel = ViewModelProvider(mainActivity).get(BasketEntityViewModel::class.java)
    val userEntityViewModel = ViewModelProvider(mainActivity).get(UserEntityViewModel::class.java)

    basketViewModel.getAllBasketLive().observe(mainActivity) {
        basketViewModel.dataList.value = it
    }
    userEntityViewModel.getCurrentUser().observe(mainActivity){
        userEntityViewModel.currentUser.value =it
    }
    Scaffold(
        topBar = {
            if (!fullScreen)

                TopAppView(navController,basketViewModel,userEntityViewModel)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = "home",
        ) {
            composable("home") {
                fullScreen = false
                HomeScreen(navController)

            }
            composable("basket") {
                fullScreen = true
                BasketListScreen(navController, basketViewModel)

            }
            composable("proceedToPayment") {
                fullScreen = true
                UserPaymentScreen(navController,basketViewModel,userEntityViewModel,mainActivity)

            }
            composable("products/{categoryId}",
                arguments = listOf(
                    navArgument("categoryId")
                    { type = NavType.LongType }
                )
            ) { backStack ->
                fullScreen = false
                val id = backStack.arguments?.getLong("categoryId")
                ThisApp.productCategoryId = id!!


                ProductScreen(id!!, navController)

            }
            composable(
                "showProduct/{productId}",
                arguments = listOf(navArgument("productId") {
                    type = NavType.LongType
                })
            ) { backStack ->
                fullScreen = true
                backStack.arguments?.getLong("productId").let {

                    ShowProductScreen(it!!, navController, basketViewModel)

                }

            }
            composable(
                "invoice/{id}",
              deepLinks = listOf(navDeepLink { uriPattern="app://onlineshopholosen.ir/{id}" }),
                arguments = listOf(navArgument("id"){type= NavType.LongType})

            ){
                backStackEntry ->
                if (userEntityViewModel.currentUser.value!=null) {
                    ThisApp.invoiceId = backStackEntry.arguments?.getLong("id")!!
                    ThisApp.token = userEntityViewModel.currentUser.value!!.token!!
                }
                InvoiceScreen(navController, backStackEntry.arguments?.getLong("id")!!)

            }
            composable("login") {
                fullScreen = true
               LoginScreen(navController,userEntityViewModel)

            }
            composable("dashboard") {
                fullScreen = true
                DashboardScreen(navController,userEntityViewModel)

            }
            composable("invoices") {
                fullScreen = true
                InvoiceListScreen(navController)

            }
            composable("editProfile") {
                fullScreen = true
                EditProfileScreen(navController,userEntityViewModel)

            }
            composable("ChangePassword") {
                fullScreen = true
                ChangePasswordScreen(navController,userEntityViewModel)

            }

        }
    }
}