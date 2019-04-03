package com.my_project.myapplication.model.data_holder

import com.my_project.myapplication.model.entities.Car
import io.reactivex.subjects.BehaviorSubject


class InfoBehaviorSubject {

    private val subject = BehaviorSubject.create<List<Car>>()

    fun setIntoSubject(data: List<Car>) {
        data.also { subject.onNext(it) }
    }

    fun getFromSubject(): BehaviorSubject<List<Car>> {
        return subject
    }
}