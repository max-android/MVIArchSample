package com.my_project.myapplication.ui.info

import com.my_project.myapplication.model.entities.Car


data class InfoState(
    val loading: Boolean = false,
    val data: List<Car>? = null,
    val error: Throwable? = null)
