package com.jw.marvelcomics.repository.api.interceptor

import com.jw.marvelcomics.BuildConfig.API_KEY_PRIVATE
import com.jw.marvelcomics.BuildConfig.API_KEY_PUBLIC
import com.jw.marvelcomics.util.md5
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter(TIME_STAMP, TIME_STAMP_VALUE)
            .addQueryParameter(API_KEY, API_KEY_PUBLIC)
            .addQueryParameter(HASH, getHashValue())
            .build()

        return chain.proceed(chain.request().newBuilder().url(newUrl).build())
    }

    private fun getHashValue(): String {
        return "$TIME_STAMP_VALUE$API_KEY_PRIVATE$API_KEY_PUBLIC".md5()
    }

    companion object {
        const val API_KEY = "apikey"
        const val HASH = "hash"
        const val TIME_STAMP = "ts"
        const val TIME_STAMP_VALUE = "1"
    }
}