package com.akash.meruva.github

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(repo::class), version = 1, exportSchema = false)
abstract  class repoDatabase : RoomDatabase()
{
    abstract fun getrepoDao() : repoDAO

    companion object{
        @Volatile
        private var INSTANCE: repoDatabase? = null

        fun getDatabase(context: Context): repoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    repoDatabase::class.java,
                    "Task_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}