package com.example.zootestapp.network

import com.example.zootestapp.data.AnimalModel
import retrofit2.http.GET
import retrofit2.http.Path


interface AnimalService {
    @GET("animals/rand")
    suspend fun getRandomAnimal(): AnimalModel

    @GET("animals/rand/{number}/")
    suspend fun getRandomListAnimal(@Path("number") number: Int): List<AnimalModel>
}
