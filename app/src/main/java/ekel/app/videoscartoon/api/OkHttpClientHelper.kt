package com.pmui.apps.api

import android.content.Context

import io.reactivex.android.BuildConfig

import java.io.File
import java.util.concurrent.TimeUnit

import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

internal object OkHttpClientHelper {

    private val DISK_CACHE_SIZE = 50 * 1024 * 1024 // 50MB

    private fun provideOkHttpClient(context: Context, debug: Boolean): OkHttpClient {
        val cacheDir = File(context.cacheDir, "http_cache")
        val cache = Cache(cacheDir, DISK_CACHE_SIZE.toLong())

        val builder = OkHttpClient.Builder().cache(cache)
        builder.connectTimeout(5, TimeUnit.MINUTES).readTimeout(5, TimeUnit.MINUTES)

        if (debug) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    fun provideOkHttpClient(context: Context): OkHttpClient {
        return provideOkHttpClient(context, BuildConfig.DEBUG)
    }

}