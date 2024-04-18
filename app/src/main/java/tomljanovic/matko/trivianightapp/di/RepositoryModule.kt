package tomljanovic.matko.trivianightapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tomljanovic.matko.trivianightapp.data.repository.TriviaRepositoryImpl
import tomljanovic.matko.trivianightapp.domain.repository.TriviaRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTriviaRepository(
        triviaRepositoryImpl: TriviaRepositoryImpl
    ): TriviaRepository
}
