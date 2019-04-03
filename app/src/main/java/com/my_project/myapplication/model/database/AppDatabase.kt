package com.my_project.myapplication.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.my_project.myapplication.model.entities.Car
import com.my_project.myapplication.model.entities.Person


@Database(entities = [Person::class, Car::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun storeDAO(): StoreDAO
}