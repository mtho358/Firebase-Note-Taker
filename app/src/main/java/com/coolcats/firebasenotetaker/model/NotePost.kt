package com.coolcats.firebasenotetaker.model

import java.time.LocalDate
import java.util.*

data class NotePost(val date: String,
                    val id: String,
                    val title: String,
                    val subject: String,
                    val body: String){

    constructor(): this("", "", "", "", "")
}
