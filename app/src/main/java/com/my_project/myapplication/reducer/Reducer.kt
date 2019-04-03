package com.my_project.myapplication.reducer


interface Reducer<S, A> {
    fun reduce(state: S, action: A): S
}