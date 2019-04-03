package com.my_project.myapplication.reducer.login

import com.my_project.myapplication.reducer.Reducer
import com.my_project.myapplication.ui.login.LoginState

class LoginReducer : Reducer<LoginState, LoginAction> {
    override fun reduce(state: LoginState, action: LoginAction): LoginState {
        return when (action) {

            is LoginAction.LoginSuccessAction -> state.copy(data = action.data)

            is LoginAction.LoginFailureAction -> state.copy(error = action.error)
        }
    }
}