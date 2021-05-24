package com.coolcats.firebasenotetaker.view.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.coolcats.firebasenotetaker.R
import com.coolcats.firebasenotetaker.model.NotePost
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.upload_fragment_layout.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class UploadFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.upload_fragment_layout, container, false)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upload_button.setOnClickListener {
            if(note_title_edittext.text.isEmpty() || note_edittext.text.isEmpty()){
                Toast.makeText(requireContext(), "Fields cannot be empty", Toast.LENGTH_LONG).show()
            }else{
                uploadNote()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun uploadNote(){
        val reference = FirebaseDatabase.getInstance().reference.child("NotePosts")
        val key = reference.push().key ?: ""


        val note = NotePost(
            "2021-05-24",
            key,
            FirebaseAuth.getInstance().currentUser?.uid.toString(),
            note_title_edittext.toString().trim(),
            note_edittext.toString()
        )
        reference.child(key).setValue(note)
        Toast.makeText(requireContext(), "Success!!", Toast.LENGTH_SHORT).show()
    }
}