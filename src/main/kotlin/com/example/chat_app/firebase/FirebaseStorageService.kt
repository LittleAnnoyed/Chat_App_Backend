package com.example.chat_app.firebase


import com.google.cloud.storage.BlobId
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.Storage
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

    @Autowired
    private lateinit var storage: Storage


    fun uploadFileToFirebaseStorage(multipartFile: MultipartFile): String {

        val filename = generateFileName(multipartFile)

        val bucket = StorageClient.getInstance(firebaseApp).bucket()
        val bucketName = bucket.name

        val map = HashMap<String, String>()
        map["firebaseStorageDownloadTokens"] = filename

        val blobId = BlobId.of(bucketName, filename)
        val blobInfo = BlobInfo.newBuilder(blobId).setMetadata(map).setContentType(".png").build()

        storage.create(blobInfo, multipartFile.bytes)

        return createDownloadUrl(filename);
    }

    private fun generateFileName(file: MultipartFile): String {
        return " ${Date().time}_${file.name.replace(" ", "_")}"
    }

    private fun createDownloadUrl(filename: String): String {
        val baseUrl = "https://firebasestorage.googleapis.com/v0/b/chat-b62d0.appspot.com"
        return "${baseUrl}/o/${filename}?alt=media&token=${filename}"
    }
}