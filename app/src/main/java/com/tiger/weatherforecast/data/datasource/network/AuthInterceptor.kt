package com.tiger.weatherforecast.data.datasource.network

import com.tiger.weatherforecast.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    //Should store api key server-side
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
            .newBuilder()
            .addQueryParameter("appid", BuildConfig.API_KEY)
            .build()
        val request = chain.request().newBuilder().url(url).build()
        println("Outgoing request to ${request.url}")
        return chain.proceed(request)
    }
}                                                                                                                            
                                                                                                                                                                                