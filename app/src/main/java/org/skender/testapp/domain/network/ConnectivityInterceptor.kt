package org.skender.testapp.domain.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class ConnectivityInterceptor @Inject constructor(val networkUtils: NetworkUtils): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        if(networkUtils.isOffline()){
            throw NoConnectivityException()
        }

        val builder: Request.Builder = chain.request().newBuilder()

        return chain.proceed(builder.build())

    }
}