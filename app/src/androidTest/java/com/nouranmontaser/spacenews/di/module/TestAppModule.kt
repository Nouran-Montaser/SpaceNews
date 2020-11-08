package com.nouranmontaser.spacenews.di.module

import android.content.Context
import androidx.room.Room
import com.nouranmontaser.spacenews.data.DataRepository
import com.nouranmontaser.spacenews.data.DataSource
import com.nouranmontaser.spacenews.data.local.LocalData
import com.nouranmontaser.spacenews.data.local.NewsDatabase
import com.nouranmontaser.spacenews.data.remote.ApiService
import com.nouranmontaser.spacenews.data.remote.RemoteData
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object TestAppModule {

    @Singleton
    @Provides
    @Named("test-retrofit")
    fun provideRetrofit(okHttpClient: OkHttpClient, @Named("test-mockServer") mockWebServer: MockWebServer): Retrofit {
        val moshi = Moshi.Builder().build()
        return Retrofit.Builder().baseUrl(mockWebServer.url("/")).client(okHttpClient).addConverterFactory(MoshiConverterFactory.create(moshi)).build()
    }

    @Provides
    @Singleton
    @Named("test-apiService")
    fun provideApiService(@Named("test-retrofit")retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    @Named("test-remoteData")
    fun provideRemoteData(@Named("test-apiService")apiService: ApiService): RemoteData = RemoteData(apiService)

    @Provides
    @Singleton
    @Named("test-localData")
    fun provideLocalData(@Named("test-db")newsDatabase: NewsDatabase): LocalData = LocalData(newsDatabase)

    @Provides
    @Named("test-db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    @Named("test-mockServer")
    fun provideMockServer() = MockWebServer()
}