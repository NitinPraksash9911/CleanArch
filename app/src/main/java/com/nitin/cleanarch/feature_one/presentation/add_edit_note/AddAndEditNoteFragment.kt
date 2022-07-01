package com.nitin.cleanarch.feature_one.presentation.add_edit_note

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nitin.cleanarch.R
import com.nitin.cleanarch.databinding.AddEditNoteFragmentBinding
import com.nitin.cleanarch.feature_one.domain.model.Note
import com.nitin.cleanarch.feature_one.presentation.notes.NoteEvent
import com.nitin.cleanarch.feature_one.presentation.notes.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddAndEditNoteFragment : Fragment(R.layout.add_edit_note_fragment) {

    lateinit var binding: AddEditNoteFragmentBinding
    private val noteViewModel by activityViewModels<NotesViewModel>()
    val args: AddAndEditNoteFragmentArgs by navArgs()
    private var noteId: Long? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddEditNoteFragmentBinding.bind(view)


        val note = args.note

        if (note != null) {
            binding.titleEt.setText(note.title)
            binding.bodyEt.setText(note.content)
            binding.root.setBackgroundColor(note.color)
            noteId = note.id
        }

        setClickEvent()
    }

    private fun setClickEvent() {

        binding.addNoteBtn.setOnClickListener {
            noteViewModel.onEvent(
                NoteEvent.AddNote(
                    Note(id = noteId ?: System.currentTimeMillis(),
                        content = binding.bodyEt.text.toString(),
                        title = binding.titleEt.text.toString(),
                        timeStamp = System.currentTimeMillis()
                    )
                )
            )
            findNavController().popBackStack()
        }
    }
}