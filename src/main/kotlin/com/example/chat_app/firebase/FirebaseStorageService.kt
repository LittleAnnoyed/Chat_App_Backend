package com.example.chat_app.firebase

import com.example.chat_app.converter.toFile
import com.google.firebase.FirebaseApp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile


@Service
class FirebaseStorageService {


    @Autowired
    private lateinit var firebaseApp: FirebaseApp


    fun uploadFileToFirebaseStorage(multipartFile: MultipartFile) : String {

        val file = multipartFile.toFile()

    }
}