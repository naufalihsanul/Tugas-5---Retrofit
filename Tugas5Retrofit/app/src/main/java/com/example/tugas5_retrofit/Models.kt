package com.example.tugas5_retrofit

import com.google.gson.annotations.SerializedName

// Request untuk Login
data class LoginRequest(
    val email: String,
    val password: String
)

// Response untuk Login
data class LoginResponse(
    val success: Boolean,
    val message: String,
    val data: LoginData?
)

data class LoginData(
    val token: String,
    val user: User
)

data class User(
    val id: Int,
    val name: String,
    val email: String
)

// Response untuk Daftar Pasien
data class PasienResponse(
    val success: Boolean,
    val data: List<Pasien>
)

data class Pasien(
    val id: Int,
    val nama: String,
    @SerializedName("tanggal_lahir") val tanggalLahir: String,
    @SerializedName("jenis_kelamin") val jenisKelamin: String,
    val alamat: String,
    @SerializedName("no_telepon") val noTelepon: String
)