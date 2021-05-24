package com.coolcats.firebasenotetaker.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.coolcats.firebasenotetaker.R
import com.coolcats.firebasenotetaker.model.NotePost
import com.coolcats.firebasenotetaker.view.fragment.UploadFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_home.*
import java.time.LocalDate


class HomePageActivity : AppCompatActivity() {

    private val adapter = NoteRecyclerviewAdapter()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        note_recyclerview.adapter = adapter
        note_recyclerview.layoutManager = LinearLayoutManager(this)

        FirebaseDatabase.getInstance().reference.child("Notes")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val notesList = mutableListOf<NotePost>()
                    snapshot.children.forEach {
                        it.getValue(NotePost::class.java)?.let { note->
                            notesList.add(note)
                        }
                    }

                    adapter.notes = notesList
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        add_note_imageview.setOnClickListener{
            val fragment = UploadFragment()

            supportFragmentManager.beginTransaction()
                .addToBackStack(fragment.tag)
                .replace(R.id.main_frame, fragment)
                .commit()
        }
    }


}