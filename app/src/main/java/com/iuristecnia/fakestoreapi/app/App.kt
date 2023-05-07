package com.iuristecnia.fakestoreapi.app

import android.app.Application
import android.util.Log

class App : Application() {
    companion object {
     //   lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
       // db = AppDatabase.init(this, CoroutineScope(Dispatchers.IO))
        //Log.d("APP", "Paso por el onCreate ")
    }
}