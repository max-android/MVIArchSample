package com.my_project.myapplication.model.network

import com.my_project.myapplication.model.entities.TokenResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {

    @FormUrlEncoded
    @POST("token")
    fun tokenRequest(
        @Field("username") username: String,
        @Field("password") password: String
    ): Single<TokenResponse>

}