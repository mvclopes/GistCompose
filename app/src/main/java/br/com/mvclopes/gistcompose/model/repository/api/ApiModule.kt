package br.com.mvclopes.gistcompose.model.repository.api

import br.com.mvclopes.gistcompose.BuildConfig
import br.com.mvclopes.gistcompose.utils.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiModule {
    private val log = HttpLoggingInterceptor()

    init {
        if (BuildConfig.DEBUG) log.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient
        .Builder()
        .addInterceptor(log)
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .followRedirects(true)
        .addInterceptor { chain: Interceptor.Chain ->
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url

            val newHttpUrl = originalHttpUrl
                .newBuilder()
                .build()
            val newRequest = originalRequest
                .newBuilder()
                .url(newHttpUrl)
                .build()
            chain.proceed(newRequest)
        }
        .build()

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .baseUrl(BASE_URL)
        .build()

    val apiNetwork: ApiService by lazy { retrofit.create(ApiService::class.java) }
}
