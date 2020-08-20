package org.skender.testapp.ui.restaurant

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_restaurants.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import net.idik.lib.slimadapter.SlimAdapter
import net.idik.lib.slimadapter.SlimAdapterEx
import org.skender.testapp.R
import org.skender.testapp.data.local.Restaurant
import org.skender.testapp.injection.components.MainComponent
import org.skender.testapp.ui.base.AbstractFragment
import org.skender.testapp.ui.base.LoadStateFragment
import org.skender.testapp.ui.restaurant.injector.RestaurantInjector
import org.skender.testapp.ui.restaurant.mvp.RestaurantContract
import org.skender.testapp.ui.restaurant.mvp.RestaurantPresenter
import javax.inject.Inject

class RestaurantFragment: AbstractFragment(), RestaurantContract {

    @Inject
    @InjectPresenter
    lateinit var presenter: RestaurantPresenter

    @ProvidePresenter
    fun providesPresenter(): RestaurantPresenter = presenter

    private lateinit var adapter: SlimAdapterEx

    override fun injectDependencies() {
        super.injectDependencies()
        getComponent(MainComponent::class.java)?.inject(this)
    }

    @SuppressLint("ResourceAsColor")
    override fun setupToolbar(toolbar: Toolbar) {
        super.setupToolbar(toolbar)
        toolbar.title = "Рестораны"
    }

    override val layoutRes: Int
        get() = R.layout.fragment_restaurants

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initLayout()
        presenter.onDataRequest()
    }



    private fun initLayout() {

        list_restaurants.layoutManager = GridLayoutManager(context, 2)

        adapter = SlimAdapter.create()

        adapter.register(R.layout.item_restaurants, RestaurantInjector())
            .attachTo(list_restaurants)

        fullscreenErrorViewMoney.setRetryClickListener {
            presenter.onDataRequest()
        }

        search.getSearch().setOnQueryTextListener(SearchTextWatcher())
    }

    override fun showLoading() {
        if (viewFlipper.displayedChild != LoadStateFragment.VIEW_PROGRESS.ordinal) {
            viewFlipper.displayedChild = LoadStateFragment.VIEW_PROGRESS.ordinal
        }
    }

    override fun showList(list: List<Restaurant>) {
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

    inner class SearchTextWatcher: SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            newText?.let { presenter.search(it) }
            return true
        }

    }
}