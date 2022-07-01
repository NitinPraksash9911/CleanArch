package com.nitin.cleanarch.feature_one.domain.use_cases

import com.nitin.cleanarch.feature_one.data.repository.NoteFakeRepository
import com.nitin.cleanarch.feature_one.domain.model.Note
import com.nitin.cleanarch.feature_one.domain.util.NoteOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetNotesTest {

    private lateinit var getNotes: GetNotes
    private lateinit var fakeRepository: NoteFakeRepository

    @Before
    fun setUp() {

        fakeRepository = NoteFakeRepository()
        getNotes = GetNotes(fakeRepository)

        val noteToInsert = mutableListOf<Note>()

        ('a'..'z').forEachIndexed { index, c ->

            noteToInsert.add(
                Note(id = index.toLong(),
                    title = c.toString(),
                    content = c.toString(),
                    timeStamp = index.toLong(),
                    color = index
                )
            )

        }.apply {
            runBlocking {
                noteToInsert.forEach {
                    fakeRepository.insertNote(it)
                }
            }
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Order notes by title`() = runTest {

        val notes = getNotes(NoteOrder.Title).first()

        for (i in 0..notes.size - 2) {

            assert((notes[i].title) < notes[i + 1].title)
        }

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Order notes by date`() = runTest {
        val notes = getNotes(NoteOrder.Date).first()

        for (i in 0..notes.size - 2) {

            assert((notes[i].timeStamp) > notes[i + 1].timeStamp)
        }
    }



    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Order notes by color`() = runTest {
        val notes = getNotes(NoteOrder.Color).first()

        for (i in 0..notes.size - 2) {

            assert((notes[i].color) < notes[i + 1].color)
        }
    }

}