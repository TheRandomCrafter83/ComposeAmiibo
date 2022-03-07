package com.example.composeamiibo.model

data class Amiibo(
    val amiiboSeries: String,
    val character: String,
    val gameSeries: String,
    val games3DS: List<Games3DS>,
    val gamesSwitch: List<GamesSwitch>,
    val gamesWiiU: List<GamesWiiU>,
    val head: String,
    val image: String,
    val name: String,
    val release: Release,
    val tail: String,
    val type: String
)