package com.example.chat_app.converter

import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.util.Objects

fun MultipartFile.toFile(): File {

    val convertFile = File(this.originalFilename!!)
    val fos = FileOutputStream(convertFile)
    fos.write(this.bytes)
    fos.close()
    return convertFile
}