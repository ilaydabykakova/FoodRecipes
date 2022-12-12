package com.ilaydabykakova.foodrecipes.data.api


import okhttp3.Interceptor
import okhttp3.Response

class RetrofitInceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
            var req = chain.request()

            val url = req.url.newBuilder().build()
            req = req.newBuilder().url(url).build()
            return chain.proceed(req)
        }

}