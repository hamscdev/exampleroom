package com.hamscdev.roomexample.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hamscdev.roomexample.data.model.ModelRoom
import com.hamscdev.roomexample.data.model.dao.DataDao


@Database(entities = [ModelRoom::class], version = 1, exportSchema = false)

abstract class RoomDataBase: RoomDatabase() {
    abstract fun dataDao(): DataDao


    companion object{
        @Volatile
        private var INSTANCE: RoomDataBase? = null

        fun getDatabase(context: Context): RoomDataBase{
            val tmpInstance = INSTANCE
            if (tmpInstance != null){
                return tmpInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    RoomDataBase::class.java,
                    "model_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}