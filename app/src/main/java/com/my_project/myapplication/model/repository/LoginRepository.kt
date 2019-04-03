package com.my_project.myapplication.model.repository

import com.my_project.myapplication.model.data_holder.LocalHolder
import com.my_project.myapplication.model.entities.TokenResponse
import com.my_project.myapplication.model.network.ApiService
import io.reactivex.Single


class LoginRepository(private val api:ApiService,private val localHolder:LocalHolder) {

    fun sendTokenRequest(login:String,password:String): Single<TokenResponse> {
        return api.tokenRequest(login,password)
    }

    fun saveToken(token:String){
        localHolder.setToken(token)
    }
}