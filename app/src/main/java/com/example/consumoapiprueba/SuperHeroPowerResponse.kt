package com.example.consumoapiprueba

import com.google.gson.annotations.SerializedName

data class SuperHeroPowerResponse(
    @SerializedName("CodigoPoder") var codigoPoder: Int,
    @SerializedName("NombrePoder") var namePoder: String
)
