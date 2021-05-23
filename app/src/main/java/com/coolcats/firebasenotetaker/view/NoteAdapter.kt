package com.coolcats.firebasenotetaker.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coolcats.firebasenotetaker.R
import com.coolcats.firebasenotetaker.model.Note
import kotlinx.android.synthetic.main.notelist_item_layout.view.*

class NoteAdapter() : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    var notes: List<Note> = mutableListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.notelist_item_layout, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.itemView.note_title_textview.text = note.title
        holder.itemView.note_textview.text = note.body
    }

    override fun getItemCount(): Int = notes.size

}