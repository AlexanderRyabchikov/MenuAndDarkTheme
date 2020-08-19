package org.skender.testapp.injection.core

import android.content.Context
import dagger.Component
import org.skender.testapp.domain.interactors.Interactor
import org.skender.testapp.domain.network.ApiInterface
import org.skender.testapp.domain.repository.Repository
import org.skender.testapp.injection.module.ApiModule
import org.skender.testapp.injection.module.AppModule
import org.skender.testapp.injection.module.RepositoryModule
import org.skender.testapp.ui.base.AbstractActivity
import org.skender.testapp.ui.base.AbstractFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, ApiModule::class])
interface AppComponent {
    fun context(): Context

    fun apiInterfaces(): ApiInterface

    fun inject(fragment: AbstractFragment)

    fun inject(activity: AbstractActivity)

    fun getRepository(): Repository

    fun getInteractor(): Interactor
}