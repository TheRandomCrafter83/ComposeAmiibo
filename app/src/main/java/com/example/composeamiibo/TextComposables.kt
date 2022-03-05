package com.example.composeamiibo

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun HeaderText(text: String) {
    Text(text, fontWeight = FontWeight.Bold, fontSize = 24.sp)
}

@Composable
fun Header2Text(text: String) {
    Text(text, fontWeight = FontWeight.Bold, fontSize = 18.sp)
}