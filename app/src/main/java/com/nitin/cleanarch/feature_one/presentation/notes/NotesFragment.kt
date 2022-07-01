package com.nitin.cleanarch.feature_one.presentation.notes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.nitin.cleanarch.R
import com.nitin.cleanarch.databinding.FragmentNotesBinding
import com.nitin.cleanarch.feature_one.core.ViewStateResource
import com.nitin.cleanarch.feature_one.domain.util.NoteOrder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NotesFragment : Fragment(R.layout.fragment_notes) {

    lateinit var binding: FragmentNotesBinding
    private lateinit var noteAdapter: NoteAdapter
    private val noteViewModel by activityViewModels<NotesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotesBinding.bind(view)

        initData()


    }

    private fun initData() {


        binding.dateChip.setOnClickListener {
            noteViewModel.onEvent(NoteEvent.Order(NoteOrder.Date))
        }

        binding.titleChip.setOnClickListener {
            noteViewModel.onEvent(NoteEvent.Order(NoteOrder.Title))
        }
        binding.bodyChip.setOnClickListener {
            noteViewModel.onEvent(NoteEvent.Order(NoteOrder.Color))
        }

        binding.addNoteBtn.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToAddAndEditNoteFragment(null)
            findNavController().navigate(action)
        }

        noteAdapter = NoteAdapter { note ->
            val action =
                    NotesFragmentDirections.actionNotesFragmentToAddAndEditNoteFragment(note = note)
            findNavController().navigate(action)
        }

        binding.noteRv.adapter = noteAdapter


        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                noteViewModel.state.collectLatest { noteState ->
                    when (noteState) {
                        is ViewStateResource.ViewError -> {

                            Toast.makeText(requireActivity(),
                                noteState.errorResponse,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        ViewStateResource.ViewLoading -> {

                        }
                        is ViewStateResource.ViewSuccess -> {
                            noteAdapter.submitList(noteState.item.notes)
                        }
                    }
                }
            }
        }

    }

}