package org.skender.testapp.ui.base.externals

import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.skender.testapp.R

fun SwipeRefreshLayout.init(onRefresh: () -> Unit) {
    this.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(context, R.color.blue_grey))
    this.setColorSchemeColors(ContextCompat.getColor(context, R.color.white))
    this.setOnRefreshListener { onRefresh() }
}

/**
 * Исправляет баг на 4.4, когда остается видимой часть индикатора,
 * если потянуть вниз, не отпуская, не рефрешить, затем вернуть наверх
 */
fun SwipeRefreshLayout.setIndicatorScale() {
    val start = this.progressViewStartOffset
    val end = this.progressViewEndOffset
    this.setProgressViewOffset(false, start, end)
}