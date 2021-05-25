package com.coolcats.firebasenotetaker.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.coolcats.firebasenotetaker.R
import com.coolcats.firebasenotetaker.model.ClassNote
import com.coolcats.firebasenotetaker.view.fragment.UploadFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_home.*


class HomePageActivity : AppCompatActivity() {

    private val adapter = NoteRecyclerviewAdapter()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        note_recyclerview.adapter = adapter
        note_recyclerview.layoutManager = LinearLayoutManager(this)

        FirebaseDatabase.getInstance().reference.child("NotePosts")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val notesList = mutableListOf<ClassNote>()
                    snapshot.children.forEach {
                        it.getValue(ClassNote::class.java)?.let { item->
                            notesList.add(item)
                        }
                    }
                    adapter.notes = notesList
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("TAG_M", "An error occured")
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