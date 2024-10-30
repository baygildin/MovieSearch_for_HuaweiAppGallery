package com.sbaygildin.search.di

import android.content.Context
import com.sbaygildin.search.ApiKeyInterceptor
import com.sbaygildin.search.OmdbApi
import com.sbaygildin.search.model.FavouriteDao
import com.sbaygildin.search.model.FavouriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val OMBD_API_PATH = "https://www.omdbapi.com"
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor(ApiKeyInterceptor())
                    .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
                    .build()
            )
            .baseUrl(OMBD_API_PATH).build()
    }

    @Singleton
    @Provides
    fun provideOmdbApi(retrofit: Retrofit): OmdbApi {
        return retrofit.create(OmdbApi::class.java)
    }

    @Singleton
    @Provides
    fun provideFavouriteDatabase(@ApplicationContext context: Context): FavouriteDatabase {
        return FavouriteDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideFavouriteDao(database: FavouriteDatabase): FavouriteDao {
        return database.favouriteDao()
    }

}