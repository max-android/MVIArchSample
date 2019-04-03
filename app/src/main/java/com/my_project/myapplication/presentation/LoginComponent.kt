package com.my_project.myapplication.presentation

import android.annotation.SuppressLint
import com.jakewharton.rxrelay2.BehaviorRelay
import com.my_project.myapplication.middleware.login.LoginMiddleware
import com.my_project.myapplication.model.data_holder.LoginBehaviorSubject
import com.my_project.myapplication.reducer.login.LoginReducer
import com.my_project.myapplication.ui.MviView
import com.my_project.myapplication.ui.login.LoginIntent
import com.my_project.myapplication.ui.login.LoginState
import io.reactivex.Observable


class LoginComponent(private val loginMiddleware: LoginMiddleware, private val loginReducer: LoginReducer) {

    private val state = BehaviorRelay.createDefault<LoginState>(LoginState())
    val subject = LoginBehaviorSubject()

    @SuppressLint("CheckResult")
    fun bind(view: MviView<LoginIntent, LoginState>): Observable<LoginState> {
        return loginMiddleware.bind(view.intent)
            .scan(state.value) { state, action -> loginReducer.reduce(state, action) }
    }
}