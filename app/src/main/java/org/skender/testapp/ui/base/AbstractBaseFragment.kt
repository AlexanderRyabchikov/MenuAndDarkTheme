package org.skender.testapp.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import moxy.MvpAppCompatFragment

abstract class AbstractBaseFragment : MvpAppCompatFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    @get: LayoutRes
    protected abstract val  layoutRes: Int

    protected open fun injectDependencies() {
        Log.d(javaClass.name, "no base implementation for dependencies injection")
    }
}