package org.skender.testapp.ui.splash.mvp

import io.reactivex.Completable
import moxy.InjectViewState
import org.skender.testapp.ui.base.AbstractPresenter
import org.skender.testapp.utils.asyncCompletable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(): AbstractPresenter<SplashContract>(), SplashContract.Presenter {

    private val SPLASH_DEFAULT_DURATION: Long = 2000

    override fun onConfigRequested() {
        val delay = Completable
            .fromAction {}
            .delay(SPLASH_DEFAULT_DURATION, TimeUnit.MILLISECONDS)
            .compose(asyncCompletable())
            .subscribe(
                { viewState.completeLoading() }
            ) { obj: Throwable -> obj.printStackTrace() }
    }
}