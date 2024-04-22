package tomljanovic.matko.trivianightapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import tomljanovic.matko.trivianightapp.data.local.TriviaDatabase
import tomljanovic.matko.trivianightapp.data.remote.TriviaApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideTriviaApi(client: OkHttpClient): TriviaApi {
        return Retrofit.Builder().baseUrl(TriviaApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build().create()
    }

    @Provides
    @Singleton
    fun provideTriviaDatabase(app: Application): TriviaDatabase {
        return Room.databaseBuilder(app, TriviaDatabase::class.java, "triviadb.db").build()
    }
}
