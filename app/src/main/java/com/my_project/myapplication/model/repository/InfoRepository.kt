package com.my_project.myapplication.model.repository

import android.annotation.SuppressLint
import com.my_project.myapplication.model.database.AppDatabase
import com.my_project.myapplication.model.entities.Car
import com.my_project.myapplication.model.entities.Person
import io.reactivex.Completable


class InfoRepository(private val database:AppDatabase) {

    @SuppressLint("CheckResult")
     fun initData():Completable {
      return  Completable.fromAction {
            database.storeDAO().insertPersons(
                listOf(
                    Person(0, "Имя1", 18, "type1"),
                    Person(1, "Имя2", 20, "type2"),
                    Person(2, "Имя3", 30, "type3"),
                    Person(3, "Имя4", 40, "type4"),
                    Person(4, "Имя5", 50, "type5"),
                    Person(5, "Имя6", 60, "type6")
                )
            )

            database.storeDAO().insertCars(
                listOf(
                    Car(0, "type1", "car_1"),
                    Car(1, "type2", "car_2"),
                    Car(2, "type3", "car_3"),
                    Car(3, "type4", "car_4"),
                    Car(4, "type5", "car_5"),
                    Car(5, "type6", "car_6")
                )
            )
        }
    }

    fun requestAllPersons() = database.storeDAO().allPersons()

    fun requestCar(type:String) = database.storeDAO().selectCar(type)

}