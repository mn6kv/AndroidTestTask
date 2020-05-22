package com.example.testtask

import android.graphics.drawable.Drawable

class User {

    private var email: String
    private var name: String
    private var secondName: String
    private var photo: Int
    private var password: String

    constructor(email: String, name: String, secondName: String,
                photo: Int, password: String) {
        this.email = email
        this.name = name
        this.secondName = secondName
        this.password = password
        this.photo = photo
        this.password = password
    }

    fun getEmail(): String = email
}