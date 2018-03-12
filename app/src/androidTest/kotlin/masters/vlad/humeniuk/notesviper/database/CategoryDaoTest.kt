package masters.vlad.humeniuk.notesviper.database

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import masters.vlad.humeniuk.notesviper.database.entity.DbCategory
import masters.vlad.humeniuk.notesviper.domain.utils.CategoryUtil
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryDaoTest {

    private lateinit var notesDatabase: NotesDatabase

    @Before
    fun init() {
        notesDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                NotesDatabase::class.java).build()
    }

    @After
    fun close() {
        notesDatabase.close()
    }

    @Test
    fun testInsert() {
        val dao = notesDatabase.categoryDao

        val category = CategoryUtil.getDefaultCategory()
        val id = dao.insert(category)
        category.id = id

        val dbCategory = dao.findAll().get(0)
        assertEquals(category, dbCategory)
    }

    @Test
    fun testUpdate() {
        val dao = notesDatabase.categoryDao

        val category = CategoryUtil.getDefaultCategory()
        val id = dao.insert(category)
        category.id = id

        category.name = "newname"
        dao.update(category)

        val dbCategory = dao.findAll().get(0)
        assertEquals(category, dbCategory)
    }

    @Test
    fun testDelete() {
        val dao = notesDatabase.categoryDao

        val category = CategoryUtil.getDefaultCategory()
        val id = dao.insert(category)
        category.id = id

        dao.delete(category)

        val dbCategories = dao.findAll()
        assertTrue(dbCategories.isEmpty())
    }

    @Test
    fun testFindAll() {
        val dao = notesDatabase.categoryDao

        val categories : MutableList<DbCategory> = arrayListOf()
        for(i in 1L..3) {
            val category = CategoryUtil.getDefaultCategory()
            category.id = i
            val id = dao.insert(category)
            category.id = id
            categories.add(category)
        }


        val dbCategories = dao.findAll()
        assertEquals(categories, dbCategories)
    }
}