package org.skender.testapp.ui.main.mvp

import moxy.InjectViewState
import org.skender.testapp.ui.base.AbstractPresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(): AbstractPresenter<MainContract>(), MainContract.Presenter {
}