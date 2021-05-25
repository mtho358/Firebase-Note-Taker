package com.coolcats.firebasenotetaker.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coolcats.firebasenotetaker.R
import com.coolcats.firebasenotetaker.model.ClassNote
import kotlinx.android.synthetic.main.notelist_item_layout.view.*

class NoteRecyclerviewAdapter() : RecyclerView.Adapter<NoteRecyclerviewAdapter.NoteViewHolder>(){

    var notes: List<ClassNote> = mutableListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notelist_item_layout, parent, false)

        return NoteViewHolder(view)
    }

    //Binds the data to the corresponding elements within the recycler view list item
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val notePost = notes[position]
        holder.itemView.apply {
            note_title_textview.text = notePost.subject.trim()
            body_textview.text = notePost.body.trim()
        }
    }

    //Returns the number of items in the list
    override fun getItemCount(): Int = notes.size
}