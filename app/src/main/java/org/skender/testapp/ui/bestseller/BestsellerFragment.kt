package org.skender.testapp.ui.bestseller

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Window
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.fragment_bestseller.*
import kotlinx.android.synthetic.main.fragment_restaurants.fullscreenErrorViewMoney
import kotlinx.android.synthetic.main.fragment_restaurants.viewFlipper
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import net.idik.lib.slimadapter.SlimAdapter
import net.idik.lib.slimadapter.SlimAdapterEx
import org.skender.testapp.R
import org.skender.testapp.data.local.Bestseller
import org.skender.testapp.injection.components.MainComponent
import org.skender.testapp.injection.module.GlideApp
import org.skender.testapp.ui.base.AbstractFragment
import org.skender.testapp.ui.base.LoadStateFragment
import org.skender.testapp.ui.base.externals.init
import org.skender.testapp.ui.base.externals.setIndicatorScale
import org.skender.testapp.ui.base.externals.show
import org.skender.testapp.ui.bestseller.injector.BestsellerInjector
import org.skender.testapp.ui.bestseller.mvp.BestsellerContract
import org.skender.testapp.ui.bestseller.mvp.BestsellerPresenter
import javax.inject.Inject


class BestsellerFragment: AbstractFragment(), BestsellerContract, BestsellerInjector.BestsellerLongClickListener {

    @Inject
    @InjectPresenter
    lateinit var presenter: BestsellerPresenter

    private lateinit var adapter: SlimAdapterEx

    override val layoutRes: Int
        get() = R.layout.fragment_bestseller

    @ProvidePresenter
    fun providesPresenter(): BestsellerPresenter = presenter

    override fun injectDependencies() {
        super.injectDependencies()
        getComponent(MainComponent::class.java)?.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initLayout()
        presenter.onDataRequest()
    }

    @SuppressLint("ResourceAsColor")
    override fun setupToolbar(toolbar: Toolbar) {
        super.setupToolbar(toolbar)
        toolbar.title = "Хиты продаж"
    }



    private fun initLayout() {

        list_bestseller.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        adapter = SlimAdapter.create()

        adapter.register(R.layout.item_bestseller, BestsellerInjector(this))
            .attachTo(list_bestseller)

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

    override fun showList(list: List<Bestseller>) {
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

    override fun longClick(url: String) {
        val nagDialog = context?.let {
            Dialog(
                it,
                R.style.ThemeOverlay_AppCompat_Dialog
            )
        }
        if (nagDialog != null) {
            nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            nagDialog.setCancelable(false)
            nagDialog.setContentView(R.layout.dialog_show_full_image)
            val ivPreview: ImageView = nagDialog.findViewById(R.id.iv_preview_image) as ImageView
            nagDialog.setCanceledOnTouchOutside(true)
            GlideApp.with(ivPreview.context)
                .asDrawable()
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivPreview)

            nagDialog.show()
        }
    }
}