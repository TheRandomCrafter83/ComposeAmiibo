package com.example.composeamiibo.model

import kotlinx.serialization.Serializable


data class Root(
    var amiibo:ArrayList<Amiibo>
)

@Serializable
data class Amiibo(
    var amiiboSeries:String = "",
    var character:String = "",
    var gameSeries:String = "",
    var head:String = "",
    var image:String = "",
    var name:String = "",
    var release:Release = Release(),
    var tail:String = "",
    var type:String = "",
)

@Serializable
data class Release(
    var au:String? = "",
    var eu:String? = "",
    var jp:String? = "",
    var na:String? = ""
)
