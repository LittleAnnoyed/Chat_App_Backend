package com.example.chat_app.user

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query


interface UserRepository: MongoRepository<User, String> {

    @Query("{login:'?0'}")
    fun findUserByLogin(login: String): User?
    fun findUserById(id: String): User
    fun updateUser(user: User)
}