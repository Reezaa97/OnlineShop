package com.example.onlineshopapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp.db.viewmodels.UserEntityViewModel
import com.example.onlineshopapp.models.cusomer.UserVm
import com.example.onlineshopapp.viewModels.customer.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavHostController,
    userEntityViewModel: UserEntityViewModel,

    userViewModel: UserViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var firstName by remember {
        mutableStateOf(TextFieldValue())
    }
    var firstNameError by remember {
        mutableStateOf(false)
    }
    var userName by remember {
        mutableStateOf(TextFieldValue())
    }
    var userNameError by remember {
        mutableStateOf(false)
    }
    var password by remember {
        mutableStateOf(TextFieldValue())
    }
    var passwordError by remember {
        mutableStateOf(false)
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    LazyColumn {
        item {

            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = ""
                )
            }
        }
        item {
            Column(Modifier.padding(20.dp)) {
                Text(
                    text = "Welcome", textAlign = TextAlign.Center,
                    fontSize = 40.sp
                )

                Spacer(
                    modifier = Modifier
                        .height(10.dp)

                )
                Text(
                    text = "Login", textAlign = TextAlign.Center,
                    fontSize = 30.sp
                )
            }


        }
        item {

            OutlinedTextField(
                value = userName, onValueChange = { userName = it },
                label = { androidx.compose.material3.Text(text = "User Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                trailingIcon = {
                    if (userNameError) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Filled.Warning, contentDescription = "error",
                            tint = Color.Red
                        )
                    }
                }, isError = userNameError
            )
            if (userNameError) {
                androidx.compose.material3.Text(
                    text = "Please enter Your User Name", color = Color.Red,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = password, onValueChange = { password = it },
                label = { androidx.compose.material3.Text(text = "Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),

                singleLine = true,


                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),

                trailingIcon = {
                    if (passwordError) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Filled.Warning, contentDescription = "error",
                            tint = Color.Red
                        )
                    }
                }, isError = passwordError

            )
            if (passwordError) {
                androidx.compose.material3.Text(
                    text = "Please enter Your password", color = Color.Red,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            if (isLoading) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(15.dp), verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Button(
                    onClick = {
                        if (userName.text.isEmpty()) {
                            userNameError = true
                        }
                        if (password.text.isEmpty()) {
                            passwordError = true
                        }
                        if (userNameError || passwordError) return@Button
                       isLoading =true
                        userViewModel.login(
                            UserVm(
                                username = userName.text,
                                password = password.text
                            )
                        ) { response ->
                            if (response.status == "OK") {
                                val user = response.data!![0]
                                CoroutineScope(Dispatchers.IO).launch {

                                    userEntityViewModel.insert(user.convertToEntity())
                                }
                                Toast.makeText(
                                    context,
                                    "Welcome ${user.firstName}",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                                navController.navigate("home")
                            }
                            isLoading =false
                        }
                    },
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Blue
                    )
                ) {
                    androidx.compose.material.Text(
                        text = "Login ",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }


            }
        }
    }
}


