package masters.vlad.humeniuk.notesviper.database

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import masters.vlad.humeniuk.notesviper.database.entity.DbNote
import masters.vlad.humeniuk.notesviper.domain.utils.CategoryUtil
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteDaoTest {

    private lateinit var notesDatabase: NotesDatabase

    @Before
    fun init() {
        notesDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                NotesDatabase::class.java).build()
        notesDatabase.categoryDao.insert(CategoryUtil.getDefaultCategory())
        var cat2 = CategoryUtil.getDefaultCategory()
        cat2.id = 2L
        notesDatabase.categoryDao.insert(cat2)
    }

    @After
    fun close() {
        notesDatabase.close()
    }

    @Test
    fun testInsert() {
        val note = getNote()

        val dao = notesDatabase.noteDao
        val id = dao.insert(note)
        note.id = id

        val dbNote = dao.findAll().get(0)
        assertEquals(note, dbNote)
    }

    @Test
    fun testUpdate() {
        val note = getNote()

        val dao = notesDatabase.noteDao
        val id = dao.insert(note)
        note.id = id

        note.title = "newtitle"
        dao.update(note)

        val dbNote = dao.findAll().get(0)
        assertEquals(note, dbNote)
    }

    @Test
    fun testDelete() {
        val note = getNote()

        val dao = notesDatabase.noteDao
        val id = dao.insert(note)
        note.id = id

        dao.delete(note)
        val dbNotes = dao.findAll()
        assertTrue(dbNotes.isEmpty())
    }

    @Test
    fun testFindAll() {
        val dao = notesDatabase.noteDao
        val notes = mutableListOf<DbNote>()
        for (i in 1..3) {
            val note = getNote()
            val id = dao.insert(note)
            note.id = id
            notes.add(note)
        }

        val dbNotes = dao.findAll()
        assertEquals(notes, dbNotes)
    }

    @Test
    fun testFindAllByCategory() {
        val dao = notesDatabase.noteDao
        val notes = mutableListOf<DbNote>()
        for (i in 1..3) {
            val note = getNote()
            val id = dao.insert(note)
            note.id = id
            notes.add(note)
        }
        var notes2 = mutableListOf<DbNote>()
        for (i in 1..4) {
            val note = getNote()
            note.categoryId = 2
            val id = dao.insert(note)
            note.id = id
            notes2.add(note)
        }

        val dbNotes = dao.findByCategory(1)
        assertEquals(notes, dbNotes)
    }

    fun getNote() : DbNote {
        val note = DbNote()
        note.categoryId = 1
        note.dateCreated = 2
        note.dateLastEdit = 3
        note.title = "title"
        note.description = "description"

        return note
    }
}