package com.example.zootestapp.di


import com.example.zootestapp.network.AnimalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://zoo-animal-api.herokuapp.com/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesBaseUrl() : String = BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL : String) : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideAnimalService(retrofit : Retrofit) : AnimalService = retrofit.create(AnimalService::class.java)
}