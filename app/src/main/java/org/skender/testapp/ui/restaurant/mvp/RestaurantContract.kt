package org.skender.testapp.ui.restaurant.mvp

import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import org.skender.testapp.data.local.Restaurant
import org.skender.testapp.ui.base.LoadDataView

@StateStrategyType(OneExecutionStateStrategy::class)
interface RestaurantContract: LoadDataView {

    fun showList(list: List<Restaurant>)

    interface Presenter {
        fun onDataRequest()
        fun search(string: String)
    }
}