package org.skender.testapp.ui.base

import android.os.Bundle
import org.skender.testapp.App
import org.skender.testapp.injection.core.AppComponent
import org.skender.testapp.injection.module.ActivityModule

abstract class AbstractActivity : AbstractBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getAppComponent().inject(this)
    }

    protected fun getAppComponent(): AppComponent = (application as App).getAppComponent()
    protected fun getActivityModule(): ActivityModule = ActivityModule(this)
}