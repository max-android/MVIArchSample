package com.my_project.myapplication.middleware

import io.reactivex.Observable


interface Middleware<A, B> {
    fun bind(intents: Observable<A>): Observable<B>
}
