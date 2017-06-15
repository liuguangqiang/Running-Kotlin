package com.liuguangqiang.kotlindemo.domian

import com.github.aurae.retrofit2.LoganSquareConverterFactory
import com.liuguangqiang.irunning.BuildConfig
import java.io.IOException

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory

/**
 * Created by Eric on 17/5/19.
 */
class RetrofitClient {

    private var retrofit: retrofit2.Retrofit? = null

    init {
        createRetrofit()
    }

    fun <T> create(clazz: Class<*>): T {
        return retrofit!!.create(clazz) as T
    }

    private fun createRetrofit() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        val okHttpClient = OkHttpClient
                .Builder()
                .addInterceptor(RequestInterceptor())
                .addInterceptor(interceptor)
                .build()

        retrofit = retrofit2.Retrofit
                .Builder()
                .baseUrl(HOST_NAME)
                .client(okHttpClient)
                .addConverterFactory(LoganSquareConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    private inner class RequestInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val builder = chain.request().newBuilder()
            builder.addHeader(HEADER_USER_AGENT, "Test")
            val response = chain.proceed(builder.build())
            return response
        }
    }

    companion object {

        private val DEV_HOST_NAME = "http://api.step.maiziui.com/"

        private val LIVE_HOST_NAME = "http://api.step.maiziui.com/"

        private val HEADER_USER_AGENT = "User-Agent"

        val HOST_NAME = if (BuildConfig.DEBUG) DEV_HOST_NAME else LIVE_HOST_NAME

        val instance = RetrofitClient()
    }

}
