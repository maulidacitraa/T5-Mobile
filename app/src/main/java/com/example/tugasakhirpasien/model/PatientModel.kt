package com.example.tugasakhirpasien.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val data: LoginData?
)

data class LoginData(
    val token: String,
    val user: UserInfo
)

data class UserInfo(
    val id: Int,
    val name: String,
    val email: String
)

data class PatientResponse(
    val success: Boolean,
    val message: String,
    val data: List<Patient>
)

data class Patient(
    val id: Int,
    val nama: String,
    @SerializedName("tanggal_lahir") val tanggalLahir: String,
    @SerializedName("jenis_kelamin") val jenisKelamin: String,
    val alamat: String,
    @SerializedName("no_telepon") val noTelepon: String
)