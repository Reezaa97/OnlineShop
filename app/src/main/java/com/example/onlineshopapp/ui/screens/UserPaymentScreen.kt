package com.example.onlineshopapp.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.navigation.NavController
import com.example.onlineshopapp.MainActivity
import com.example.onlineshopapp.db.viewmodels.BasketEntityViewModel
import com.example.onlineshopapp.db.viewmodels.UserEntityViewModel
import com.example.onlineshopapp.models.cusomer.UserVm
import com.example.onlineshopapp.models.invoice.InvoiceItem
import com.example.onlineshopapp.models.invoice.PaymentTransaction
import com.example.onlineshopapp.viewModels.customer.UserViewModel
import com.example.onlineshopapp.viewModels.invoice.TransactionViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun UserPaymentScreen(
    navController: NavController,
    basketViewModel: BasketEntityViewModel,
    userEntityViewModel: UserEntityViewModel,
    mainActivity: MainActivity,
    transactionViewModel: TransactionViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var currentUser by remember {
        mutableStateOf(userEntityViewModel.currentUser)
    }
    val isLogin by remember{ mutableStateOf(userEntityViewModel.currentUser.value !=null) }
    var firstName by remember {
        mutableStateOf(TextFieldValue(if (isLogin)currentUser.value!!.firstName!!else ""))
    }
    var firstNameError by remember {
        mutableStateOf(false)
    }
    var lastName by remember {
        mutableStateOf(TextFieldValue(if (isLogin)currentUser.value!!.lastName!!else ""))
    }
    var lastNameError by remember {
        mutableStateOf(false)
    }
    var phone by remember {
        mutableStateOf(TextFieldValue(if (isLogin)currentUser.value!!.phone!!else ""))
    }
    var phoneError by remember {
        mutableStateOf(false)
    }
    var postalCode by remember {
        mutableStateOf(TextFieldValue(if (isLogin)currentUser.value!!.postalCode!!else ""))
    }
    var postalCodeError by remember {
        mutableStateOf(false)
    }
    var address by remember {
        mutableStateOf(TextFieldValue(if (isLogin)currentUser.value!!.address!!else ""))
    }
    var addressError by remember {
        mutableStateOf(false)
    }
    var userName by remember {
        mutableStateOf(TextFieldValue(if (isLogin)currentUser.value!!.username!!else ""))
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
    var focusManager= LocalFocusManager.current
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
                    keyboardActions = KeyboardActions(onNext ={
                        focusManager.moveFocus(FocusDirection.Down)
                    } ),


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
                    keyboardActions = KeyboardActions(onNext ={
                        focusManager.moveFocus(FocusDirection.Down)
                    } ),
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
                    keyboardActions = KeyboardActions(onNext ={
                        focusManager.moveFocus(FocusDirection.Down)
                    } ),
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
                    enabled=currentUser.value ==null,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext ={
                        focusManager.moveFocus(FocusDirection.Down)
                    } ),
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
                if (currentUser.value == null) {
                    OutlinedTextField(
                        value = password, onValueChange = { password = it },
                        label = { Text(text = "Password") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),

                        singleLine = true,


                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext ={
                            focusManager.moveFocus(FocusDirection.Down)
                        } ),

                        trailingIcon = {
                            if (passwordError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )
                            }
                        }, isError = passwordError

                    )
                    if (passwordError) {
                        Text(
                            text = "Please enter Your password", color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
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
                    keyboardActions = KeyboardActions(onNext ={
                        focusManager.moveFocus(FocusDirection.Down)
                    } ),
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
                    keyboardActions = KeyboardActions(onNext ={
                        focusManager.moveFocus(FocusDirection.Down)
                    } ),
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

                            if (currentUser.value == null && password.text.isEmpty()) {
                                passwordError = true
                            }
                            if (address.text.isEmpty()) {
                                addressError = true
                            }
                            if (postalCode.text.isEmpty()) {
                                postalCodeError = true
                            }
                            if (firstNameError || lastNameError || userNameError || passwordError || phoneError || addressError || postalCodeError) {
                                return@Button
                            }
                            var userInfo = UserVm(
                                id=if (currentUser.value==null) null
                                else currentUser.value!!.userId,
                                customerId=if (currentUser.value==null) null
                                else currentUser.value!!.customerId,
                                username = userName.text,
                                password = password.text,
                                firstName = firstName.text,
                                lastName = lastName.text,
                                phone = phone.text,
                                address = address.text,
                                postalCode = postalCode.text,

                                )


                            var items = ArrayList<InvoiceItem>()
                            basketViewModel.dataList.value.forEach {
                                items.add(InvoiceItem.convertFromBasket(it))
                            }
                            var request = PaymentTransaction(
                                user = userInfo,
                                items = items
                            )
                            isLoading = true
                            transactionViewModel.goToPayment(request) { response ->

                                if (response.status == "OK" && response.data!!.isNotEmpty()) {
                                    if (currentUser.value == null) {
                                        userViewModel.login(
                                            UserVm(
                                                username = userName.text,
                                                password = password.text
                                            )
                                        ) { userResponse ->
                                            if (userResponse.status == "OK") {
                                                val user = userResponse.data!![0]
                                                CoroutineScope(Dispatchers.IO).launch {
                                                    userEntityViewModel.insert(user.convertToEntity())
                                                }
                                            }
                                        }
                                    }
                                    CoroutineScope(Dispatchers.IO).launch {
                                        basketViewModel.deleteAll()
                                        mainActivity.finish()
                                    }

                                    val intent =
                                        Intent(Intent.ACTION_VIEW, Uri.parse(response.data!![0]))
                                    context.startActivity(intent)
                                } else if (response.message!!.isNotEmpty())
                                    Toast.makeText(context, response.message!!, Toast.LENGTH_LONG)
                                        .show()
                            }
                            isLoading = false
                        },
                        shape = RoundedCornerShape(20),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Green
                        )
                    ) {
                        androidx.compose.material.Text(
                            text = " \$Pay",
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

