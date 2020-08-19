package org.skender.testapp.ui.splash

import android.os.Bundle
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.skender.testapp.R
import org.skender.testapp.injection.components.DaggerMainComponent
import org.skender.testapp.ui.base.AbstractActivity
import org.skender.testapp.ui.main.MainActivity
import org.skender.testapp.ui.splash.mvp.SplashContract
import org.skender.testapp.ui.splash.mvp.SplashPresenter
import javax.inject.Inject

class SplashScreen : AbstractActivity(), SplashContract {
    override val layoutRes: Int
        get() = R.layout.activity_splash

    @Inject
    @InjectPresenter
    lateinit var presenter: SplashPresenter

    @ProvidePresenter
    fun providePresenter(): SplashPresenter = presenter

    override fun injectDependencies() {
        super.injectDependencies()
        DaggerMainComponent.builder()
            .appComponent(getAppComponent())
            .activityModule(getActivityModule())
            .build()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onConfigRequested()
    }

    override fun completeLoading() {
        startActivity(MainActivity.createStartIntent(this))
    }

}