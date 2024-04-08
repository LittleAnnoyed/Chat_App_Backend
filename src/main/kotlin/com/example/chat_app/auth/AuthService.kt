package com.example.chat_app.auth

import com.example.chat_app.user.User
import com.example.chat_app.user.UserRepository
import com.example.chat_app.user.UserRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService: UserDetailsService {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails? {
        return try {
            val user = userRepository.findUserByLogin(username!!)
            user
        } catch (exception: Exception) {
            null
        }
    }

    fun signUp(data: SignUpDto): UserDetails? {
        if (userRepository.findUserByLogin(data.login) != null) {
            println("User exists")
            return null
        }
        val encryptedPassword = BCryptPasswordEncoder().encode(data.password)
        val newUser = User(login = data.login,password = encryptedPassword,role = UserRole.USER)
        return userRepository.save(newUser)
    }
}