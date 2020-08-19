package org.skender.testapp.ui.base

import androidx.annotation.StringRes
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface LoadDataView : MvpView {

    fun showData()

    fun showLoading()

    fun showError(ex: Throwable)
}