package com.coolcats.firebasenotetaker.model

class Note(val date: String, val title: String, val body: String){
    constructor():this("", "", "")
}

