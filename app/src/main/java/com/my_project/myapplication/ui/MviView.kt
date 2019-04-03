package com.my_project.myapplication.ui

import io.reactivex.Observable


interface MviView<A, S> {
    val intent: Observable<A>
    fun render(state: S)
}

