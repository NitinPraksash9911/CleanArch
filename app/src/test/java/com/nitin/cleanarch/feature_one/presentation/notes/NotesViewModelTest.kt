package com.nitin.cleanarch.feature_one.presentation.notes

import com.nitin.cleanarch.feature_one.data.repository.NoteFakeRepository
import com.nitin.cleanarch.feature_one.data.repository.NotePrefFakeRepository
import com.nitin.cleanarch.feature_one.domain.model.Note
import com.nitin.cleanarch.feature_one.domain.use_cases.AddNote
import com.nitin.cleanarch.feature_one.domain.use_cases.DeleteNote
import com.nitin.cleanarch.feature_one.domain.use_cases.GetNoteOrder
import com.nitin.cleanarch.feature_one.domain.use_cases.GetNotes
import com.nitin.cleanarch.feature_one.domain.use_cases.NoteUseCase
import com.nitin.cleanarch.feature_one.domain.use_cases.SaveNoteOrder
import com.nitin.cleanarch.feature_one.utils.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NotesViewModelTest {


    private lateinit var notesViewModel: NotesViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(TestDispatcherProvider())

    lateinit var noteUseCase: NoteUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        noteUseCase = NoteUseCase(
            getNotes = GetNotes(NoteFakeRepository()),
            insertNote = AddNote(NoteFakeRepository()),
            deleteNote = DeleteNote(NoteFakeRepository()),
            getNoteOrder = GetNoteOrder(NotePrefFakeRepository()),
            saveNoteOrder = SaveNoteOrder(NotePrefFakeRepository()),
        )

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `check saved order`() = runTest {

        val note = Note(1L, "A", "A", 1L, 1)

//
//
//        notesViewModel = NotesViewModel(noteUseCase, mainDispatcherRule.testDispatcherProvider)
//
//        assert(notesViewModel.state.value == ViewStateResource.ViewSuccess(NotesState(listOf(
//            note
//        )
//        )
//        )
//        )


    }


}