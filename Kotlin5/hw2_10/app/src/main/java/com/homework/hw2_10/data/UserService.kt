package com.homework.hw2_10.data


object UserService {
    private var usersList = mutableListOf(
        User("Kirill", "Ryabikin","kirill@gmail.com", "123")
    )

    fun addUser(user: User): Boolean {
        return if (usersList.any { it.email == user.email }) {
            false
        }
        else {
            usersList.add(user)
            true
        }
    }


    fun authUser(
        email: String,
        password: String
    ): Boolean {
        return usersList.any { it.email == email && it.password == password }
    }


    fun getUser(email: String): User? {
        return usersList.find { it.email == email }
    }
}





