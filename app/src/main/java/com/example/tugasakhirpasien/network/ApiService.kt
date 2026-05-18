package com.example.tugasakhirpasien.network // SESUAIKAN PACKAGE

import com.example.tugasakhirpasien.model.LoginResponse // SESUAIKAN IMPORT
import com.example.tugasakhirpasien.model.PatientResponse // SESUAIKAN IMPORT
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("api/login")
    suspend fun login(
        @Body request: Map<String, String>
    ): Response<LoginResponse>

    @GET("api/pasien")
    suspend fun getPatients(
        @Header("Authorization") token: String
    ): Response<PatientResponse>

    companion object {
        private const val BASE_URL = "https://api.pahrul.my.id/"

        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}