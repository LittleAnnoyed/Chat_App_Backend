package com.example.chat_app.auth

import com.example.chat_app.security.TokenProvider
import com.example.chat_app.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/auth")
class AuthController {

    @Autowired
    private lateinit var authManager: AuthenticationManager
    @Autowired
    private lateinit var service: AuthService
    @Autowired
    private lateinit var tokenProvider: TokenProvider


    @PostMapping("/signup")
    fun signUp(@RequestBody signUpDto: SignUpDto) : ResponseEntity<Unit> {
        service.signUp(signUpDto)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody signInDto: SignInDto): ResponseEntity<JwtDto> {
        val usernamePassword = UsernamePasswordAuthenticationToken(signInDto.login,signInDto.password)
        val authUser = authManager.authenticate(usernamePassword)
        val accessToken = tokenProvider.generateAccessToken(authUser.principal as User)
        return ResponseEntity.ok(JwtDto(accessToken!!))
    }
}