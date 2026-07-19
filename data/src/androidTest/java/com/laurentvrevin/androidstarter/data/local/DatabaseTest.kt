package com.laurentvrevin.androidstarter.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.laurentvrevin.androidstarter.data.local.dao.SampleDao
import com.laurentvrevin.androidstarter.data.local.entities.SampleEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var sampleDao: SampleDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        sampleDao = db.sampleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeSampleAndReadInList() = runBlocking {
        val sample = SampleEntity(1, "Title", "Desc")
        sampleDao.insert(sample)
        val samples = sampleDao.getAllSamples().first()
        assertEquals(samples[0], sample)
    }

    @Test
    @Throws(Exception::class)
    fun writeSampleAndGetById() = runBlocking {
        val sample = SampleEntity(2, "Test", "Desc")
        sampleDao.upsert(sample)
        val loaded = sampleDao.getSampleById(2)
        assertEquals(loaded?.title, "Test")
    }
}
