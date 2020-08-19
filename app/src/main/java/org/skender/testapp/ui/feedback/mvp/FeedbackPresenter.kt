package org.skender.testapp.ui.feedback.mvp

import moxy.InjectViewState
import org.skender.testapp.domain.interactors.Interactor
import org.skender.testapp.domain.mapper.FeedbackRemoteToLocalMapper
import org.skender.testapp.ui.base.AbstractPresenter
import org.skender.testapp.utils.asyncSingle
import javax.inject.Inject

@InjectViewState
class FeedbackPresenter @Inject constructor(
    private val interactor: Interactor,
    private val mapper: FeedbackRemoteToLocalMapper
) : AbstractPresenter<FeedbackContract>(), FeedbackContract.Presenter {

    override fun onDataRequest() {
        subscribe(
            interactor
                .getListFeedback()
                .compose(asyncSingle())
                .doOnSubscribe { viewState.showLoading() }
                .doOnSuccess { viewState.showData() }
                .map(mapper::map)
                .subscribe({
                    if (it != null) {
                        viewState.showList(it)
                    }
                }, this::handleError)
        )
    }
}