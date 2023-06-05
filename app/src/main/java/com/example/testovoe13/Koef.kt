package com.example.testovoe13

import com.google.gson.annotations.SerializedName

data class Koef(

    @SerializedName("total") val total : String,
    @SerializedName("bolshe") val bolshe : String,
    @SerializedName("menshe") val menshe : String
)
