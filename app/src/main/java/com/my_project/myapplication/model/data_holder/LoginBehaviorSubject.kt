package com.my_project.myapplication.model.data_holder

import io.reactivex.subjects.BehaviorSubject


class LoginBehaviorSubject {
   private val subject = BehaviorSubject.create<Pair<String,String>>()

    fun setIntoSubject(data: Pair<String,String>) {
        data.also { subject.onNext(it) }
    }

    fun getFromSubject():BehaviorSubject<Pair<String,String>>{
        return subject
    }
}