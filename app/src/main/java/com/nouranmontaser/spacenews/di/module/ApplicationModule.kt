package com.nouranmontaser.spacenews.di.module

import android.content.Context
import androidx.room.Room
import com.nouranmontaser.spacenews.BuildConfig
import com.nouranmontaser.spacenews.data.DataRepository
import com.nouranmontaser.spacenews.data.DataSource
import com.nouranmontaser.spacenews.data.local.DatabaseConfig
import com.nouranmontaser.spacenews.data.local.LocalData
import com.nouranmontaser.spacenews.data.local.NewsDatabase
import com.nouranmontaser.spacenews.data.remote.ApiService
import com.nouranmontaser.spacenews.data.remote.RemoteData
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().callTimeout(1, TimeUnit.MINUTES).addInterceptor(loggingInterceptor).build()
    } else OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient, BASE_URL: String): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(MoshiConverterFactory.create(moshi).asLenient()).build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRemoteData(apiService: ApiService): RemoteData = RemoteData(apiService)

    @Provides
    @Singleton
    fun provideLocalData(newsDatabase: NewsDatabase): LocalData = LocalData(newsDatabase)

    @Singleton
    @Provides
    fun provideDataRepository(
        localData: LocalData,
        remoteData: RemoteData
    ): DataSource = DataRepository(localData, remoteData)

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(context, NewsDatabase::class.java, DatabaseConfig.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
}