package com.my_project.myapplication.model.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "car")
data class Car(@PrimaryKey val id: Int,
               val type: String,
               val model: String)
