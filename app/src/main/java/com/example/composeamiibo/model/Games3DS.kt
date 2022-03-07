package com.example.composeamiibo.model

data class Games3DS(
    val amiiboUsage: List<AmiiboUsage>,
    val gameID: List<String>,
    val gameName: String
)