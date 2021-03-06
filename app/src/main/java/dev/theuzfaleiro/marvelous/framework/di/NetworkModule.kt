package dev.theuzfaleiro.marvelous.framework.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.theuzfaleiro.marvelous.BuildConfig
import dev.theuzfaleiro.marvelous.framework.di.qualifier.BaseUrl
import dev.theuzfaleiro.marvelous.framework.network.MarvelService
import dev.theuzfaleiro.marvelous.framework.network.interceptor.AuthorizationInterceptor
import java.util.Calendar
import java.util.TimeZone
import java.util.concurrent.TimeUnit.SECONDS
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TIMEOUT_SECONDS = 15L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesNetworkService(retrofit: Retrofit): MarvelService =
        retrofit.create(MarvelService::class.java)

    @Provides
    fun providesMoshiConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        }

    @Provides
    fun providesAuthorizationInterceptor(): AuthorizationInterceptor =
        AuthorizationInterceptor(
            publicKey = BuildConfig.PUBLIC_KEY,
            privateKey = BuildConfig.PRIVATE_KEY,
            calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        )


    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: AuthorizationInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authorizationInterceptor)
            .readTimeout(TIMEOUT_SECONDS, SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, SECONDS)
            .build()

    @Provides
    fun providesRetrofit(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
}

