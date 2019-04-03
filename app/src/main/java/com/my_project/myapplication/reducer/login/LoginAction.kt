package com.my_project.myapplication.reducer.login

import com.my_project.myapplication.model.entities.TokenResponse


sealed class LoginAction {
    class LoginSuccessAction(val data: TokenResponse) : LoginAction()
    class LoginFailureAction(val error: Throwable) : LoginAction()
}