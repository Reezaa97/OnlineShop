package com.example.onlineshopapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdvancedButton(
    title: String,
    subTitle: String,
    imageVector: ImageVector,
    iconBackgroundColor:Color,
    onClick: () -> Unit
) {
    Card(onClick = { onClick() }, elevation = 0.dp) {

        Row(Modifier.padding(20.dp)) {


            Card(
                modifier = Modifier.padding(5.dp),
                shape = RoundedCornerShape(50.dp),
                backgroundColor = iconBackgroundColor

            ) {


                Icon(
                    imageVector = imageVector, contentDescription = "",
                    Modifier.size(50.dp), tint = Color.White
                )
            }
                Spacer(modifier = Modifier.width(20.dp))
                Column(Modifier.weight(1f)) {
                    Text(text = title, fontSize = 22.sp)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = subTitle, fontSize = 18.sp)


                }
            }
        }
    }



