package org.skender.testapp

import androidx.multidex.MultiDexApplication
import org.skender.testapp.injection.core.AppComponent
import org.skender.testapp.injection.core.DaggerAppComponent
import org.skender.testapp.injection.module.ApiModule
import org.skender.testapp.injection.module.AppModule

class App : MultiDexApplication() {
    private lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()

        initComponents()
    }

    private fun initComponents() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .apiModule(ApiModule(BuildConfig.ServerUrl + BuildConfig.API))
            .build()
    }

    fun getAppComponent() :AppComponent = appComponent

}