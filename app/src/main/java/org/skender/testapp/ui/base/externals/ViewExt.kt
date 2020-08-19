package org.skender.testapp.ui.base.externals

import android.view.View

fun View.show(): View {
    visibility = View.VISIBLE
    return this
}

fun View.hide() {
    visibility = View.GONE
}