package org.skender.testapp.ui.feedback

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_bestseller.*
import kotlinx.android.synthetic.main.fragment_bestseller.fullscreenErrorViewMoney
import kotlinx.android.synthetic.main.fragment_bestseller.swipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_bestseller.viewFlipper
import kotlinx.android.synthetic.main.fragment_feedback.*
import kotlinx.android.synthetic.main.fragment_restaurants.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import net.idik.lib.slimadapter.SlimAdapter
import net.idik.lib.slimadapter.SlimAdapterEx
import org.skender.testapp.R
import org.skender.testapp.data.local.Bestseller
import org.skender.testapp.data.local.Feedback
import org.skender.testapp.injection.components.MainComponent
import org.skender.testapp.ui.base.AbstractFragment
import org.skender.testapp.ui.base.LoadStateFragment
import org.skender.testapp.ui.base.externals.init
import org.skender.testapp.ui.base.externals.setIndicatorScale
import org.skender.testapp.ui.bestseller.injector.BestsellerInjector
import org.skender.testapp.ui.feedback.injector.FeedbackInjector
import org.skender.testapp.ui.feedback.mvp.FeedbackContract
import org.skender.testapp.ui.feedback.mvp.FeedbackPresenter
import org.skender.testapp.ui.restaurant.mvp.RestaurantPresenter
import javax.inject.Inject

class FeedbackFragment: AbstractFragment(), FeedbackContract {

    @Inject
    @InjectPresenter
    lateinit var presenter: FeedbackPresenter

    private lateinit var adapter: SlimAdapterEx

    @ProvidePresenter
    fun providesPresenter(): FeedbackPresenter = presenter

    override fun injectDependencies() {
        super.injectDependencies()
        getComponent(MainComponent::class.java)?.inject(this)
    }

    override val layoutRes: Int
        get() = R.layout.fragment_feedback

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initLayout()
        presenter.onDataRequest()
    }

    @SuppressLint("ResourceAsColor")
    override fun setupToolbar(toolbar: Toolbar) {
        super.setupToolbar(toolbar)
        toolbar.title = "Отзывы"
    }



    private fun initLayout() {

        list_feedback.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        adapter = SlimAdapter.create()

        adapter.register(R.layout.item_feedback, FeedbackInjector())
            .attachTo(list_feedback)

        fullscreenErrorViewMoney.setRetryClickListener {
            presenter.onDataRequest()
        }

        swipeRefreshLayout.setIndicatorScale()
        swipeRefreshLayout.init {
            swipeRefreshLayout.isRefreshing = false
            presenter.onDataRequest()
        }
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = false
        if (viewFlipper.displayedChild != LoadStateFragment.VIEW_PROGRESS.ordinal) {
            viewFlipper.displayedChild = LoadStateFragment.VIEW_PROGRESS.ordinal
        }
    }

    override fun showList(list: List<Feedback>) {
        adapter.updateData(list)
    }

    override fun showData() {
        if (viewFlipper.displayedChild != LoadStateFragment.VIEW_DATA.ordinal) {
            viewFlipper.displayedChild = LoadStateFragment.VIEW_DATA.ordinal
        }
    }

    override fun showError(ex: Throwable) {
        if (viewFlipper.displayedChild != LoadStateFragment.VIEW_ERROR.ordinal) {
            viewFlipper.displayedChild = LoadStateFragment.VIEW_ERROR.ordinal
            fullscreenErrorViewMoney.showError(ex)
        }
    }
}