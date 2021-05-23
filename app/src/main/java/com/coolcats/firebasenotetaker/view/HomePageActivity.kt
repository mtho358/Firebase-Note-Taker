package com.coolcats.firebasenotetaker.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.coolcats.firebasenotetaker.R
import com.coolcats.firebasenotetaker.model.Note
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_home_page.*


class HomePageActivity : AppCompatActivity() {

    private val noteAdapter = NoteAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        notes_recyclerview.adapter = noteAdapter

        FirebaseDatabase.getInstance().reference.child("NotePosts")
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val noteList = mutableListOf<Note>()
                    snapshot.children.forEach {
                        it.getValue(Note::class.java)?.let { item ->
                            noteList.add(item)
                        }
                    }

                    noteAdapter.notes = noteList
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })

        //upload_button.setOnClickListener {
            //val fragment = UploadFragment()

//           supportFragmentManager.beginTransaction()
//               .addSharedElement(fragment.tag)
//                .replace(R.id.main_frame, fragment)
//                .commit()
        //}
    }

}