package com.gmail.part1.satrio.ini_tugas_kotlin.Service

import com.gmail.part1.satrio.ini_tugas_kotlin.Pojo.Berita_Pojo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    fun requestNews(@Query("country") negara: String,
                    @Query("category") kategory: String,
                    @Query("apiKey") apiKey: String
    ): Call<Berita_Pojo>
}