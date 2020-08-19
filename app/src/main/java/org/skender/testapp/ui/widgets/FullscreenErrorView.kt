package org.skender.testapp.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import org.skender.testapp.R

/**
 * Created by Irene Khramova on 01.02.2019.
 */
class FullscreenErrorView : LinearLayoutCompat {

    companion object {
        private const val UNKNOWN_ERROR_TEXT = R.string.error_unknown

        private const val UNKNOWN_ERROR_ICON = R.drawable.ic_error_unknown
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val textViewError: TextView
    private val imageViewError: ImageView
    private val buttonRetry: Button

    init {
        LayoutInflater.from(context).inflate(R.layout.view_fullscreen_error, this)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        orientation = VERTICAL
        textViewError = findViewById(R.id.textViewError) as TextView
        imageViewError = findViewById(R.id.imageViewError) as ImageView
        buttonRetry = findViewById(R.id.buttonRetry) as Button
    }

    fun setRetryClickListener(onRetryClickListener: () -> Unit) {
        buttonRetry.setOnClickListener { onRetryClickListener.invoke() }
    }

    fun showError(ex: Throwable) {
            textViewError.text = context.getString(UNKNOWN_ERROR_TEXT)
            imageViewError.setImageResource(UNKNOWN_ERROR_ICON)
    }
}