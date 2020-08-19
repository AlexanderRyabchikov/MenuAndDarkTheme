package org.skender.testapp.ui.feedback.mvp

import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import org.skender.testapp.data.local.Feedback
import org.skender.testapp.ui.base.LoadDataView

@StateStrategyType(OneExecutionStateStrategy::class)
interface FeedbackContract: LoadDataView {

    fun showList(list: List<Feedback>)
    interface Presenter {

       fun onDataRequest()
    }
}