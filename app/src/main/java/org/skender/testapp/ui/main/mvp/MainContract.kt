package org.skender.testapp.ui.main.mvp

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface MainContract: MvpView {

    interface Presenter {

    }
}