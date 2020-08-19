package org.skender.testapp.ui.bestseller.mvp

import moxy.InjectViewState
import org.skender.testapp.domain.interactors.Interactor
import org.skender.testapp.domain.mapper.BestsellerRemoteToLocalMapper
import org.skender.testapp.ui.base.AbstractPresenter
import org.skender.testapp.utils.asyncSingle
import javax.inject.Inject

@InjectViewState
class BestsellerPresenter @Inject constructor(
    private val interactor: Interactor,
    private val mapper: BestsellerRemoteToLocalMapper
) : AbstractPresenter<BestsellerContract>(), BestsellerContract.Presenter {

    override fun onDataRequest() {
        subscribe(
            interactor
                .getListBestseller()
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