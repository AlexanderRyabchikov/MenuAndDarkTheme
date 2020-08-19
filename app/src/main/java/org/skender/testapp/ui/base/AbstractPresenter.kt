package org.skender.testapp.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import moxy.MvpView

abstract class AbstractPresenter<V: MvpView> : MvpPresenter<V>() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun subscribe(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    fun handleError(throwable: Throwable?) {
        val loadDataView = viewState as LoadDataView
        throwable?.let { loadDataView.showError(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

}