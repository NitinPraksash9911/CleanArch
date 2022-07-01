package com.nitin.cleanarch.feature_one.domain.use_cases

import com.nitin.cleanarch.feature_one.data.repository.NoteFakeRepository
import com.nitin.cleanarch.feature_one.domain.model.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddNoteTest{

    lateinit var addNote: AddNote
    lateinit var fakeRepository: NoteFakeRepository


    @Before
    fun setUp(){
        fakeRepository = NoteFakeRepository()
        addNote = AddNote(fakeRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Add Note`()= runTest {

        val note = Note(1L,"asd","sad",1L,1);
        addNote.invoke(note)
        assert(fakeRepository.getNotes().first()[0] == note)

    }

}