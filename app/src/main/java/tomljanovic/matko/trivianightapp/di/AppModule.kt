package tomljanovic.matko.trivianightapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import tomljanovic.matko.trivianightapp.data.local.TriviaDatabase
import tomljanovic.matko.trivianightapp.data.remote.TriviaApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTriviaApi(): TriviaApi {
        return Retrofit.Builder().baseUrl(TriviaApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create()
    }

    @Provides
    @Singleton
    fun provideTriviaDatabase(app: Application): TriviaDatabase {
        return Room.databaseBuilder(app, TriviaDatabase::class.java, "triviadb.db").build()
    }
}
