package com.my_project.myapplication.model.database

import android.arch.persistence.room.Room
import android.content.Context



fun provideDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "db_store").build()
}