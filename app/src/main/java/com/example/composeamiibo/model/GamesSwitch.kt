package com.example.composeamiibo.model

data class GamesSwitch(
    val amiiboUsage: List<AmiiboUsage>,
    val gameID: List<String>,
    val gameName: String
)