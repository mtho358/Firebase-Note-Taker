package com.coolcats.firebasenotetaker.view.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.coolcats.firebasenotetaker.R
import com.coolcats.firebasenotetaker.model.ClassNote
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.upload_fragment_layout.*

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
            if(subject_edittext.text.isEmpty() || note_body_edittext.text.isEmpty()){
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


        val note = ClassNote(
            key,
            subject_edittext.text.toString().trim(),
            note_body_edittext.text.toString().trim()
        )
        reference.child(key).setValue(note)
        Toast.makeText(requireContext(), "Success!!", Toast.LENGTH_SHORT).show()

        val count = requireActivity().supportFragmentManager.backStackEntryCount
        for(i in 0..count) requireActivity().supportFragmentManager.popBackStack()
    }

}