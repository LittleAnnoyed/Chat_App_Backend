package com.example.chat_app.user

import com.example.chat_app.firebase.FirebaseStorageService
import com.google.cloud.storage.Storage
import com.google.firebase.FirebaseApp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile


@Service
class UserService {


    @Autowired
    private lateinit var firebaseApp: FirebaseApp

    @Autowired
    private lateinit var storage: Storage

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var firebaseService: FirebaseStorageService


    public fun updateUserProfile(userDto: UserProfileDto, userImage: MultipartFile) {
        val user: User = userRepository.findUserById(userDto.userId)
        user.username = userDto.username
        user.photoUrl = firebaseService.uploadFileToFirebaseStorage(userImage)
        userRepository.updateUser(user)
    }

}