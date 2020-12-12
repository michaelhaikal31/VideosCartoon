package com.pmui.apps.api

import android.content.Context

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiHelper private constructor(context: Context) {

    private val okHttpClient: OkHttpClient

    init {
        okHttpClient = OkHttpClientHelper.provideOkHttpClient(context)
    }

    private fun provideRestAdapter(url: String?): Retrofit {
        if (url != null) {
            BASE_URL = url
        }

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun <S> getService(serviceClass: Class<S>, url: String?): S {
        return provideRestAdapter(url).create(serviceClass)
    }

    companion object {
        var BASE_URL = "https://next.json-generator.com"
        fun getInstance(context: Context): ApiHelper {
            return ApiHelper(context)
        }
    }
}