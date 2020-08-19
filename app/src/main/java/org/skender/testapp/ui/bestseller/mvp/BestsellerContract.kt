package org.skender.testapp.ui.bestseller.mvp

import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import org.skender.testapp.data.local.Bestseller
import org.skender.testapp.ui.base.LoadDataView

@StateStrategyType(OneExecutionStateStrategy::class)
interface BestsellerContract: LoadDataView {

    fun showList(list: List<Bestseller>)

    interface Presenter {
        fun onDataRequest()
    }
}