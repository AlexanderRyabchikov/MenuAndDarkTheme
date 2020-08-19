package org.skender.testapp.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_restaurants.*
import org.skender.testapp.App
import org.skender.testapp.injection.core.AppComponent
import org.skender.testapp.injection.module.HasComponent

abstract class AbstractFragment: AbstractBaseFragment(), LoadDataView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAppComponent().inject(this)
        initToolbar()
    }

    protected fun getAppComponent(): AppComponent =
        (activity?.application as App).getAppComponent()

    protected fun <C> getComponent(componentType: Class<C>): C? {
        return componentType.cast(convertFromActivity<HasComponent<C>>().getComponent())
    }

    private fun initToolbar() {
        setupToolbar(toolbar)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> Fragment.convertFromActivity(): T = activity as T

    protected open fun setupToolbar(toolbar: Toolbar) {
        // nothing
    }

}