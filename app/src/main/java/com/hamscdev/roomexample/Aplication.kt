package com.hamscdev.roomexample

import android.app.Application
import com.hamscdev.roomexample.data.database.RoomDataBase

class Aplication: Application() {

    companion object {
        lateinit var dao: RoomDataBase
    }


    override fun onCreate() {
        super.onCreate()
       dao = RoomDataBase.getDatabase(applicationContext)
    }
}