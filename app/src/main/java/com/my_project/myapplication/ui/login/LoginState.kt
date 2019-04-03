package com.my_project.myapplication.ui.login

import com.my_project.myapplication.model.entities.TokenResponse



data class LoginState(
    val data: TokenResponse? = null,
    val error: Throwable? = null)
