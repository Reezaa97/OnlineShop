package com.example.onlineshopapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.onlineshopapp.db.viewmodels.UserEntityViewModel
import com.example.onlineshopapp.ui.components.AdvancedButton
import com.example.onlineshopapp.utils.ThisApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(navController: NavHostController, userEntityViewModel: UserEntityViewModel) {
    val currentUser by remember {
        mutableStateOf(userEntityViewModel.currentUser)

    }
    Column {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")

        }
        Row {
            Card(
                modifier = Modifier.padding(20.dp),
                shape = RoundedCornerShape(30.dp),
                backgroundColor = Color.LightGray

            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "",
                    Modifier.size(80.dp),
                    tint = Color.White,
                )
            }

            Spacer(modifier = Modifier.width(15.dp))
            Column(
                Modifier
                    .weight(1f)
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
            ) {
                Text(text = "${currentUser.value!!.firstName}", fontSize = 22.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "${currentUser.value!!.username}", fontSize = 18.sp)


            }
            IconButton(onClick = {
                ThisApp.token = currentUser.value!!.token!!
                navController.navigate("editProfile")
            }) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "")

            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Account", Modifier.padding(20.dp), fontSize = 25.sp)
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            item {

                AdvancedButton(
                    title = "Invoices",
                    subTitle = "Show your Invoices",
                    imageVector = Icons.Outlined.Star,
                    iconBackgroundColor = Color.Blue
                ) {
                    ThisApp.userId = currentUser.value!!.userId
                    ThisApp.token = currentUser.value!!.token!!
                    navController.navigate("invoices")

                }
                AdvancedButton(
                    title = "ChangePassword",
                    subTitle = "Change Your Password",
                    imageVector = Icons.Outlined.Lock,
                    iconBackgroundColor = Color.Green

                ) {
                    navController.navigate("ChangePassword")
                }
                AdvancedButton(
                    title = "Help",
                    subTitle = "Help and Feedback",
                    imageVector = Icons.Outlined.Info,
                    iconBackgroundColor = Color.Yellow

                ) {

                }
                AdvancedButton(
                    title = "LogOut",
                    subTitle = "LogOut",
                    imageVector = Icons.Outlined.ExitToApp,
                    iconBackgroundColor = Color.Red

                ) {
                    CoroutineScope(Dispatchers.IO).launch {
                        userEntityViewModel.deleteAll()
                    }
                    navController.navigate("home")

                }

            }
        }
    }
}


