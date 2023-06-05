package com.example.testovoe13

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Cards(

    @SerializedName("liga") val liga : String,
    @SerializedName("time") val time : String,
    @SerializedName("team1") val team1 : String,
    @SerializedName("image1") val image1 : String,
    @SerializedName("team2") val team2 : String,
    @SerializedName("image2") val image2 : String,
    @SerializedName("stadium") val stadium : String,
    @SerializedName("koef1") val koef1 : String,
    @SerializedName("koef2") val koef2 : String,
    @SerializedName("koef3") val koef3 : String
) : Serializable
