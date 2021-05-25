package com.coolcats.firebasenotetaker.model

class ClassNote(val date: String,
                val id: String,
                val subject: String,
                val body: String){

    constructor(): this("", "", "", "")
}

