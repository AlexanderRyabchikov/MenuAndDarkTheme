package org.skender.testapp.ui.splash.mvp

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface SplashContract: MvpView {

    fun completeLoading()

    interface Presenter {
        fun onConfigRequested()
    }
}