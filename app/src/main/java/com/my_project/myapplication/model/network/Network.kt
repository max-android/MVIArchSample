package com.my_project.myapplication.model.network

import com.my_project.myapplication.ui.common.CONST
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class Network {

    private fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    private fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .client(provideOkHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(CONST.BASE_URL)
        .build()

    fun provideApiService(): ApiService = provideRetrofit().create(ApiService::class.java)
}
