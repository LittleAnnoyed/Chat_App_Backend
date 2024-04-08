package com.example.chat_app.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.chat_app.user.User
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset



@Service
class TokenProvider {

    val secretKey = "secret_key"

    fun generateAccessToken(user: User): String? {
        return try {

            val algorithm = Algorithm.HMAC256(secretKey)
            JWT.create()
                .withSubject(user.username)
                .withClaim("username",user.username)
                .withExpiresAt(generateExpirationDate())
                .sign(algorithm)

        } catch (exception: Exception) {
            println("Generate Access Token ${exception.message}")
            null
        }
    }

    fun validateToken(token: String): String? {
        return try {
            val algorithm = Algorithm.HMAC256(secretKey)
            return JWT.require(algorithm)
                .build()
                .verify(token)
                .subject
        } catch (exception : Exception) {
            println("Validate Token Error : ${exception.message}")
            null
        }
    }

    private fun generateExpirationDate(): Instant {
        return LocalDateTime.now().plusYears(1).toInstant(ZoneOffset.of("-03:00"))
    }
}