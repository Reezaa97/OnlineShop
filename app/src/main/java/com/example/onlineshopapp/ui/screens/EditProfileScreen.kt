package com.example.onlineshopapp.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
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
fun EditProfileScreen(
    navController: NavHostController,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var currentUser by remember {
        mutableStateOf(userEntityViewModel.currentUser)
    }
    val isLogin by remember { mutableStateOf(userEntityViewModel.currentUser.value != null) }

    var firstName by remember {
        mutableStateOf(TextFieldValue(if (isLogin) currentUser.value!!.firstName!! else ""))
    }
    var firstNameError by remember {
        mutableStateOf(false)
    }
    var lastName by remember {
        mutableStateOf(TextFieldValue(if (isLogin) currentUser.value!!.lastName!! else ""))
    }
    var lastNameError by remember {
        mutableStateOf(false)
    }
    var phone by remember {
        mutableStateOf(TextFieldValue(if (isLogin) currentUser.value!!.phone!! else ""))
    }
    var phoneError by remember {
        mutableStateOf(false)
    }
    var postalCode by remember {
        mutableStateOf(TextFieldValue(if (isLogin) currentUser.value!!.postalCode!! else ""))
    }
    var postalCodeError by remember {
        mutableStateOf(false)
    }
    var address by remember {
        mutableStateOf(TextFieldValue(if (isLogin) currentUser.value!!.address!! else ""))
    }
    var addressError by remember {
        mutableStateOf(false)
    }
    var userName by remember {
        mutableStateOf(TextFieldValue(if (isLogin) currentUser.value!!.username!! else ""))
    }
    var userNameError by remember {
        mutableStateOf(false)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current

    Column {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Complete Your Information",
                textAlign = TextAlign.Center, fontSize = 22.sp,
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
            )

        }

        Spacer(modifier = Modifier.height(20.dp))



        LazyColumn {

            item {
                OutlinedTextField(
                    value = firstName, onValueChange = {
                        firstName = it
                        firstNameError = false
                    },
                    label = { Text(text = "First Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),


                    trailingIcon = {
                        if (firstNameError) {
                            Icon(
                                imageVector = Icons.Filled.Warning, contentDescription = "error",
                                tint = Color.Red
                            )
                        }
                    }, isError = firstNameError
                )
                if (firstNameError) {
                    Text(
                        text = "Please enter Your first Name", color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = lastName, onValueChange = { lastName = it },
                    label = { Text(text = "Last Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    trailingIcon = {
                        if (lastNameError) {
                            Icon(
                                imageVector = Icons.Filled.Warning, contentDescription = "error",
                                tint = Color.Red
                            )
                        }
                    }, isError = lastNameError

                )
                if (lastNameError) {
                    Text(
                        text = "Please enter Your last Name", color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = phone, onValueChange = { phone = it },
                    label = { Text(text = "Phone") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    singleLine = true,

                    trailingIcon = {
                        if (phoneError) {
                            Icon(
                                imageVector = Icons.Filled.Warning, contentDescription = "error",
                                tint = Color.Red
                            )
                        }
                    }, isError = phoneError
                )
                if (phoneError) {
                    Text(
                        text = "Please enter Your phone", color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = userName, onValueChange = { userName = it },
                    label = { Text(text = "User Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    singleLine = true,
                    enabled = currentUser.value == null,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    trailingIcon = {
                        if (userNameError) {
                            Icon(
                                imageVector = Icons.Filled.Warning, contentDescription = "error",
                                tint = Color.Red
                            )
                        }
                    }, isError = userNameError
                )
                if (userNameError) {
                    Text(
                        text = "Please enter Your User Name", color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = address, onValueChange = { address = it },
                    label = { Text(text = "Address") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    trailingIcon = {
                        if (addressError) {
                            Icon(
                                imageVector = Icons.Filled.Warning, contentDescription = "error",
                                tint = Color.Red
                            )
                        }
                    }, isError = addressError
                )
                if (addressError) {
                    Text(
                        text = "Please enter Your Address", color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = postalCode, onValueChange = { postalCode = it },
                    label = { Text(text = "Postal Code") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,

                        ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    trailingIcon = {
                        if (postalCodeError) {
                            Icon(
                                imageVector = Icons.Filled.Warning, contentDescription = "error",
                                tint = Color.Red
                            )
                        }
                    }, isError = postalCodeError
                )
                if (postalCodeError) {
                    Text(
                        text = "Please enter Your postalCode", color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (!isLoading) {

                    Button(
                        onClick = {
                            if (firstName.text.isEmpty()) {
                                firstNameError = true
                            }
                            if (lastName.text.isEmpty()) {
                                lastNameError = true
                            }
                            if (phone.text.isEmpty()) {
                                phoneError = true
                            }
                            if (userName.text.isEmpty()) {
                                userNameError = true
                            }


                            if (address.text.isEmpty()) {
                                addressError = true
                            }
                            if (postalCode.text.isEmpty()) {
                                postalCodeError = true
                            }
                            if (firstNameError || lastNameError || userNameError || phoneError || addressError || postalCodeError) {
                                return@Button
                            }
                            var userInfo = UserVm(
                                id = currentUser.value!!.userId,

                                customerId = currentUser.value!!.customerId,
                                username = userName.text,
                                firstName = firstName.text,
                                lastName = lastName.text,
                                phone = phone.text,
                                address = address.text,
                                postalCode = postalCode.text,

                                )
                            isLoading=true
                            userViewModel.update(userInfo){response ->
                               if (response.status=="OK"){
                                  CoroutineScope(Dispatchers.IO).launch {
                                       userEntityViewModel.update(userInfo.convertToEntity())
                                   }
                                   navController.popBackStack()
                               }
                                isLoading=false
                            }
                        },
                        shape = RoundedCornerShape(20),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Green
                        )
                    ) {
                        Text(
                            text = "update Profile",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                if (isLoading) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()

                    }
                }
            }
        }
    }
}


