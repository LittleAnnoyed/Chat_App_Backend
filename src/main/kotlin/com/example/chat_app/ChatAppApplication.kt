package com.example.chat_app

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.io.FileInputStream

@SpringBootApplication
class ChatAppApplication

fun main(args: Array<String>) {
	runApplication<ChatAppApplication>(*args)
}


@Bean
fun firebaseApp(): FirebaseApp {

	val serviceAccount = FileInputStream("src/main/resources/static/account_key.json")
	val options = FirebaseOptions.builder()
		.setCredentials(GoogleCredentials.fromStream(serviceAccount))
		.setDatabaseUrl("https://console.firebase.google.com")
		.setStorageBucket("chat-b62d0.appspot.com")
		.build()
	val firebaseApp = FirebaseApp.initializeApp(options,"appName");
	return firebaseApp
}

@Bean
fun initializeStorage() : Storage {

	val serviceAccount = FileInputStream("src/main/resources/static/account_key.json")
	val storageOptions = StorageOptions.newBuilder()
		.setCredentials(GoogleCredentials.fromStream(serviceAccount))
		.build()
	return storageOptions.service
}