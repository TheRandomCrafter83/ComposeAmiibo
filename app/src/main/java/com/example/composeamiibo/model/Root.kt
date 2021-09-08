package com.example.composeamiibo.model

data class Root(
    var amiibo:ArrayList<Amiibo>
)

data class Amiibo(
    var amiiboSeries:String = "",
    var character:String = "",
    var gameSeries:String = "",
    var head:String = "",
    var image:String = "",
    var name:String = "",
    var release:Release,
    var tail:String = "",
    var type:String = "",

)

data class Release(
    var au:String = "",
    var eu:String = "",
    var jp:String = "",
    var na:String = ""
)
