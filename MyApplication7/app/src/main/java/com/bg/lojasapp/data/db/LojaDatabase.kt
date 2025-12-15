package com.bg.lojasapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bg.lojasapp.model.Loja
import com.bg.lojasapp.data.dao.LojaDao

@Database(entities = [Loja::class], version = 1)
abstract class LojaDatabase : RoomDatabase() {
    abstract fun lojaDao(): LojaDao
    companion object {
        @Volatile
        private var INSTANCE: LojaDatabase? = null

        fun getInstance(context: Context): LojaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LojaDatabase::class.java,
                    "agenda_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}