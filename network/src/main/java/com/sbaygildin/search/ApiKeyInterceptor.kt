package com.sbaygildin.search

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

const val API_KEY = "60b041be"//"3896198"new apikey of mine //"60b041be" apikey of mine // "360c2147" apikey of P

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url: HttpUrl = request.url.newBuilder().addQueryParameter("apiKey", API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}