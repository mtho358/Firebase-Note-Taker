package com.coolcats.firebasenotetaker.model

data class User(var email: String, var password: String){
    constructor():this("", "")
}
