package com.example.composeamiibo.model

data class GamesWiiU(
    val amiiboUsage: List<AmiiboUsage>,
    val gameID: List<String>,
    val gameName: String
)