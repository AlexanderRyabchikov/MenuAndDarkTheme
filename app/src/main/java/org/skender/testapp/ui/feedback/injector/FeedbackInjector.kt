package org.skender.testapp.ui.feedback.injector

import android.widget.ImageView
import net.idik.lib.slimadapter.SlimInjector
import net.idik.lib.slimadapter.viewinjector.IViewInjector
import org.skender.testapp.R
import org.skender.testapp.data.local.Feedback
import org.skender.testapp.utils.formatDate

class FeedbackInjector :SlimInjector<Feedback> {

    override fun onInject(data: Feedback, injector: IViewInjector<IViewInjector<*>>) {

        if (data.isPositive) {
            val imageView = injector.findViewById<ImageView>(R.id.rating)
            imageView.setImageResource(R.drawable.ic_positive)
        } else {
            val imageView = injector.findViewById<ImageView>(R.id.rating)
            imageView.setImageResource(R.drawable.ic_negative)
        }

        injector.text(R.id.message, data.message)
        injector.text(R.id.name, data.userName)
        injector.text(R.id.restaurant_name, data.restaurantName)
        injector.text(R.id.date, formatDate(data.date))
    }
}