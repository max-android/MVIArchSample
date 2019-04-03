package com.my_project.myapplication.model.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.my_project.myapplication.model.entities.Car
import com.my_project.myapplication.model.entities.Person
import io.reactivex.Single


@Dao
interface StoreDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPersons(persons: List<Person>)

    @Query("SELECT * FROM person")
    fun allPersons(): Single<List<Person>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCars(persons: List<Car>)

    @Query("SELECT * FROM car WHERE type = :type")
    fun selectCar(type:String):Car

}