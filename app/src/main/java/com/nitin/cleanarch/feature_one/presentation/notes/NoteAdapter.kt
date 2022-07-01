package com.nitin.cleanarch.feature_one.presentation.notes

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nitin.cleanarch.databinding.NoteItemBinding
import com.nitin.cleanarch.feature_one.domain.model.Note

class NoteAdapter(private val onItemClick: (Note) -> Unit) :
    ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.binding(getItem(position))
    }


    class NoteViewHolder(
        private val binding: NoteItemBinding,
        private val onItemClick: (Note) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {


        fun binding(note: Note) {
            binding.contentTv.text = note.content
            binding.titleTv.text = note.title
            binding.cardId.setCardBackgroundColor(note.color)
            binding.root.setOnClickListener {
                onItemClick.invoke(note)
            }
            val date: String = DateFormat.format("EEE, d MMM yy h:mm a", note.timeStamp).toString()
            binding.dateTv.text = date
        }
    }


    object NoteDiffUtil : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {

            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return (oldItem.content == newItem.content)
                    && (oldItem.title == newItem.title)
                    && (oldItem.timeStamp == newItem.timeStamp)
        }

    }


}