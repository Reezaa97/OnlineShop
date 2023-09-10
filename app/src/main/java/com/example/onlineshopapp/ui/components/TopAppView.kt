package com.example.onlineshopapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.onlineshopapp.db.viewmodels.BasketEntityViewModel
import com.example.onlineshopapp.db.viewmodels.UserEntityViewModel

@Composable
fun TopAppView(
    navController: NavHostController,
    basketViewModel: BasketEntityViewModel,
    userEntityViewModel: UserEntityViewModel
) {
    val animatedVisibleState =remember{MutableTransitionState(false).
    apply { targetState=true }
    }
    TopAppBar(
        title = {
            AnimatedVisibility(visibleState = animatedVisibleState,
            enter = slideInVertically (animationSpec = tween(500),
            initialOffsetY = {-40}
                )+
                    fadeIn(
                        animationSpec = tween(500)
                    ),
                exit = fadeOut()
                ) {
                Text(
                    text = "Online Shop",
                    fontSize = 25.sp
                )
            }

        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = { navController.navigate("basket") }) {

                Box(contentAlignment = Alignment.BottomEnd) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "", Modifier.padding(2.dp)
                    )
                    if (basketViewModel.dataList.value.isNotEmpty()) {

                        Card(
                            shape = RoundedCornerShape(50.dp),
                            backgroundColor = Color.Red,
                        ) {
                            Text(
                                text = "${basketViewModel.dataList.value.size}",
                                color = Color.White,
                                fontSize = 15.sp
                            )

                        }

                    }
                }

            }

            IconButton(onClick = {
                if (userEntityViewModel.currentUser.value==null){
                    navController.navigate("login")
                }
                else
                {
                    navController.navigate("dashboard")
                }
                }) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = ""
                )
            }

        }
    )


}