package com.example.consumoapiprueba

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIServices {
    @GET
    suspend fun GetSuperHeroPower(@Url url:String):Response<SuperHeroPowerResponse>
}