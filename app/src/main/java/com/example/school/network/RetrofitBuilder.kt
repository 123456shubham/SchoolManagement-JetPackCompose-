package com.example.school.network

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier

@Module
@InstallIn(SingletonComponent::class)
class RetrofitBuilder {

    // Base URL for the API
    private val BASE_URL = "http:/192.168.0.103:8080/"

    @Singleton
    @Provides
    fun provideOkHttpClient(application: Application): OkHttpClient {
        return OkHttpClient.Builder()
            // Add Interceptor for headers
            .addInterceptor { chain ->
                val original = chain.request()
                val request: Request = original.newBuilder()
                    .header("Authorization", "Bearer ") // Add token here
                    .header("Content-Type", "application/json") // Example of a common header
                    .build()
                chain.proceed(request)
            }
            // Add Interceptor for handling errors like 401
            .addInterceptor { chain ->
                val response: Response = chain.proceed(chain.request())
                if (response.code == 401) {
                    // Handle 401 Unauthorized
                    // Example: Log the user out, refresh token, etc.
                    throw Exception("Unauthorized - Please check your authentication token.")
                }
                response
            }

            .addInterceptor(ChuckerInterceptor(application))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .hostnameVerifier(HostnameVerifier { hostname, session -> true })
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // Attach the custom OkHttpClient
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): RestService {
        return retrofit.create(RestService::class.java)
    }
}
