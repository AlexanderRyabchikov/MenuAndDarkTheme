package org.skender.testapp.injection.module

import dagger.Binds
import dagger.Module
import org.skender.testapp.domain.interactors.Interactor
import org.skender.testapp.domain.interactors.InteractorImpl
import org.skender.testapp.domain.repository.Repository
import org.skender.testapp.domain.repository.RepositoryImpl
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideRepository(repository: RepositoryImpl): Repository


    @Binds
    fun provideInteractor(interactor: InteractorImpl): Interactor
}