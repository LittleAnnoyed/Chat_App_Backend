package com.example.chat_app.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile


@Controller
@RequestMapping("user/")
class UserController {


    @Autowired
    private lateinit var userService: UserService


    @PostMapping("set_user_data")
    public fun setUserData(
        @RequestParam userImage: MultipartFile,
        @RequestParam username: UserProfileDto
    ):ResponseEntity<Unit> {

        val response = userService.updateUserProfile(username,userImage)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("get_users")
    public fun getUsers(
        @RequestParam page: Int,
        @RequestParam pageSize: Int
    ): ResponseEntity<List<User>> {
        val users : List<User> = userService.loadUsers(page, pageSize)
        return ResponseEntity(users,HttpStatus.OK)
    }
}