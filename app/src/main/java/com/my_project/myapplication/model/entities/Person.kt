package com.my_project.myapplication.model.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "person")
data class Person(@PrimaryKey val id: Int,
                  val name: String,
                  val age: Int,
                  val type: String)
