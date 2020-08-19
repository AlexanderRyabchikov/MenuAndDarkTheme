package org.skender.testapp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.skender.testapp.R
import org.skender.testapp.injection.components.DaggerMainComponent
import org.skender.testapp.injection.components.MainComponent
import org.skender.testapp.injection.module.HasComponent
import org.skender.testapp.ui.base.AbstractActivity
import org.skender.testapp.ui.main.mvp.MainContract
import org.skender.testapp.ui.main.mvp.MainPresenter
import javax.inject.Inject

class MainActivity : AbstractActivity(), MainContract, HasComponent<MainComponent> {

    companion object {

        fun createStartIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    private lateinit var mainComponent: MainComponent

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = presenter

    override fun getComponent(): MainComponent = mainComponent

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun injectDependencies(){
        super.injectDependencies()
        mainComponent = DaggerMainComponent
            .builder()
            .appComponent(getAppComponent())
            .activityModule(getActivityModule())
            .build()

        mainComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NavigationUI.setupWithNavController(bottomNavigationViewMain, Navigation.findNavController(this, R.id.viewContentContainer))


    }
}