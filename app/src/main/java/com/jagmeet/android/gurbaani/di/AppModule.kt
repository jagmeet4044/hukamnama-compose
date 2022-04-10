package com.jagmeet.android.gurbaani.di

import com.jagmeet.android.gurbaani.datasource.repository.hukamnama.HukamnamaRepository
import android.app.Application
import androidx.room.Room
import com.jagmeet.android.gurbaani.Constants
import com.jagmeet.android.gurbaani.datasource.database.HukamnamaDao
import com.jagmeet.android.gurbaani.datasource.database.HukamnamaDb
import com.jagmeet.android.gurbaani.datasource.network.hukamnama.TodayHukamnamaService
import com.jagmeet.android.gurbaani.datasource.repository.hukamnama.HukamnamaRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    companion object {
        @Provides
        @Singleton
        fun weatherServiceApi(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.HUKAMNAMA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
        }

        private fun getOkHttpClient(): OkHttpClient {
            val client = OkHttpClient.Builder()
            return client.build()
        }

        @Provides
        @Singleton
        fun hukamnamaService(retrofit: Retrofit): TodayHukamnamaService =
            retrofit.create(TodayHukamnamaService::class.java)

        @Singleton
        @Provides
        fun provideAppDb(app: Application): HukamnamaDb {
            return Room
                .databaseBuilder(app, HukamnamaDb::class.java, HukamnamaDb.DATABASE_NAME)
                .fallbackToDestructiveMigration() // get correct db version if schema changed
                .build()
        }

        @Singleton
        @Provides
        fun provideHukamnamaDao(db: HukamnamaDb): HukamnamaDao {
            return db.getHukamnamaDao()
        }
    }


    @Binds
    abstract fun bindHukamnamaRepository(
        hukamnamaRepository: HukamnamaRepositoryImpl
    ): HukamnamaRepository

}