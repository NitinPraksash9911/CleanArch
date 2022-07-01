package com.nitin.cleanarch.feature_one.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nitin.cleanarch.feature_one.core.ViewStateResource
import com.nitin.cleanarch.feature_one.domain.use_cases.NoteUseCase
import com.nitin.cleanarch.feature_one.domain.util.NoteOrder
import com.nitin.cleanarch.feature_one.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase,
    private val coroutineDispatcher: DispatcherProvider
) : ViewModel() {


    private var _state =
            MutableStateFlow<ViewStateResource<NotesState>>(ViewStateResource.ViewLoading)
    val state: StateFlow<ViewStateResource<NotesState>> = _state

    private var job: Job? = null

    init {
        getNotes()
    }

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch(coroutineDispatcher.main) {
                    noteUseCase.deleteNote(event.note)
                }
            }
            is NoteEvent.Order -> {
                viewModelScope.launch(coroutineDispatcher.main) {
                    noteUseCase.saveNoteOrder(event.noteOrder)
                }
            }
            is NoteEvent.AddNote -> {
                viewModelScope.launch(coroutineDispatcher.main) {
                    try {
                        noteUseCase.insertNote(event.note)
                    } catch (e: Exception) {
                        _state.value = ViewStateResource.ViewError(e.localizedMessage!!)
                    }
                }
            }
        }
    }

    private fun getNotes() {
        viewModelScope.launch(coroutineDispatcher.main) {
            noteUseCase.getNoteOrder().collect {
                getNotes(NoteOrder.values()[it])
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        try {
            job?.cancel()
            job = noteUseCase.getNotes(noteOrder).onEach { notes ->
                _state.value = ViewStateResource.ViewSuccess(NotesState(notes, noteOrder))
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            _state.value = ViewStateResource.ViewError(e.localizedMessage!!)
        }

    }
}