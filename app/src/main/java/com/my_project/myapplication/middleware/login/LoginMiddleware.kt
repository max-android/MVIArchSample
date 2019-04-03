package com.my_project.myapplication.middleware.login

import com.my_project.myapplication.middleware.Middleware
import com.my_project.myapplication.model.repository.LoginRepository
import com.my_project.myapplication.reducer.login.LoginAction
import com.my_project.myapplication.ui.login.LoginIntent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LoginMiddleware(private val loginRepository: LoginRepository) : Middleware<LoginIntent, LoginAction> {
    override fun bind(intents: Observable<LoginIntent>): Observable<LoginAction> {
        return intents.flatMapSingle {
            loginRepository.sendTokenRequest(it.login, it.password)
                .doOnSuccess { tokenResponse -> loginRepository.saveToken(tokenResponse.token) }
                .map<LoginAction> { tokenResponse -> LoginAction.LoginSuccessAction(tokenResponse) }
                .onErrorReturn { e -> LoginAction.LoginFailureAction(e) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}

