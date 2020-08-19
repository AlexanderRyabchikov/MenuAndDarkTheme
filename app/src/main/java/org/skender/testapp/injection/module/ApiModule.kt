package org.skender.testapp.injection.module

import android.content.Context
import androidx.multidex.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.skender.testapp.domain.network.ApiInterface
import org.skender.testapp.domain.network.ConnectivityInterceptor
import org.skender.testapp.domain.network.NetworkUtils
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule (private val endPoint: String){
    private val CACHE_SIZE: Long = 10*1024*1024
    private val DATE_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ssZ"
    private val TIMEOUT: Long = 30

    @Provides
    @Singleton
    fun provideHttpCache(context: Context): Cache = Cache(context.cacheDir, CACHE_SIZE)


    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setDateFormat(DATE_FORMAT)
        .create()

    @Provides
    @Singleton
    fun provideHttpLogginInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideConnectivityInterceptor(networkUtils: NetworkUtils)
            : ConnectivityInterceptor = ConnectivityInterceptor(networkUtils)

    @Provides
    @Singleton
    fun provideOkHttpCLient(cache: Cache,
                            loggingInterceptor: HttpLoggingInterceptor,
                            connectivityInterceptor: ConnectivityInterceptor): OkHttpClient {


        if(BuildConfig.DEBUG){

            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(connectivityInterceptor)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .cache(cache)
                .build()

        }else{

            return OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .cache(cache)
                .build()


        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endPoint)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)
}