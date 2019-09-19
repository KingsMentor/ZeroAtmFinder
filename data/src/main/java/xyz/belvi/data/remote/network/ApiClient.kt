package xyz.belvi.data.remote.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Nosa Belvi on 6/23/18.
 * @ApiClient is a singleton class implementation for performing retrofit request
 * @apiClient for making request from any class
 * @provideHttpClient is base httpclient for request
 */


object ApiClient {

    private const val API_BASE_URL = "https://maps.googleapis.com/"


    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            ) // for serialization. Great resource for json parsing
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // for rx. Enable the use of Observable instead of {@link Call}
            .client(signedClient())
            .build()
    }



    private fun signedClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptor).build()
    }


}












