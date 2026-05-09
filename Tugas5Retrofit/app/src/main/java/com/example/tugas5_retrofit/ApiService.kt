package com.example.tugas5_retrofit

import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("api/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @GET("api/pasien")
    suspend fun getPasien(
        @Header("Authorization") token: String
    ): Response<PasienResponse>
}