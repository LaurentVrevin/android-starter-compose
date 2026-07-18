package com.laurentvrevin.androidstarter.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.laurentvrevin.androidstarter.data.local.dao.UserDao
import com.laurentvrevin.androidstarter.data.local.entities.UserEntity
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

    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() = runBlocking {
        val user = UserEntity("1", "Laurent", "laurent@example.com")
        userDao.insert(user)
        val users = userDao.getAllUsers().first()
        assertEquals(users[0], user)
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndGetById() = runBlocking {
        val user = UserEntity("2", "Test User", "test@example.com")
        userDao.upsert(user)
        val loaded = userDao.getUserById("2")
        assertEquals(loaded?.name, "Test User")
    }
}
