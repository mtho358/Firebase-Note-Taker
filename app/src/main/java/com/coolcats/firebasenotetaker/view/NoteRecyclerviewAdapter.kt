package com.coolcats.firebasenotetaker.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coolcats.firebasenotetaker.R
import com.coolcats.firebasenotetaker.model.NotePost
import kotlinx.android.synthetic.main.notelist_item_layout.view.*

class NoteRecyclerviewAdapter() : RecyclerView.Adapter<NoteRecyclerviewAdapter.NoteViewHolder>(){

    var notes: List<NotePost> = mutableListOf()

    inner class NoteViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notelist_item_layout, parent, false)

        return NoteViewHolder(view)
    }

    //Binds the data to the corresponding elements within the recycler view list item
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.itemView.apply {
            note_title_textview.text = notes[position].title
        }
    }

    //Returns the number of items in the list
    override fun getItemCount(): Int = notes.size
}