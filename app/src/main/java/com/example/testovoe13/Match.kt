package com.example.testovoe13

import com.google.gson.annotations.SerializedName

data class Match(

    @SerializedName("id") val id : Int,
    @SerializedName("team1") val team1 : String,
    @SerializedName("team2") val team2 : String,
    @SerializedName("time") val time : String,
    @SerializedName("koef1") val koef1 : String,
    @SerializedName("koef2") val koef2 : String,
    @SerializedName("koef3") val koef3 : String,
    @SerializedName("current") var current : Int
)