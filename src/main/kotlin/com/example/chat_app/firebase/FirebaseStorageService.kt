package com.example.chat_app.firebase

import com.example.chat_app.converter.toFile
import com.google.firebase.FirebaseApp
import com.google.firebase.cloud.StorageClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.Date


@Service
class FirebaseStorageService {


    @Autowired
    private lateinit var firebaseApp: FirebaseApp


    fun uploadFileToFirebaseStorage(multipartFile: MultipartFile) : String {

        val file = multipartFile.toFile()
        val pathFile = file.path
        val filename = generateFileName(file)

        val bucket = StorageClient.getInstance(firebaseApp).bucket()
        val bucketName = bucket.name


    }

    private fun generateFileName(file: File): String {
        return " ${Date().time}_${file.name.replace(" ","_")}"
    }
}