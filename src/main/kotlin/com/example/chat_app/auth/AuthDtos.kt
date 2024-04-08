package com.example.chat_app.auth

data class SignUpDto(
    val login: String,
    val password: String,
    val role: String
)

data class SignInDto(
    val login: String,
    val password: String
)

data class JwtDto(
    val accessToken: String
)