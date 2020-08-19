package org.skender.testapp.utils

import android.content.Context

class Utils {
    companion object {
        /**
         * Convert a given number in DP to the corresponding number in pixel for the current device in use
         */
        fun convertDpToPixel(context: Context, dp: Float): Float {
            val metrics = context.resources.displayMetrics
            val px = dp * (metrics.densityDpi / 160f)
            return Math.round(px).toFloat()
        }
    }
}