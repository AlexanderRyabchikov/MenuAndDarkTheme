package org.skender.testapp.ui.restaurant.injector

import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import net.idik.lib.slimadapter.SlimInjector
import net.idik.lib.slimadapter.viewinjector.IViewInjector
import org.skender.testapp.R
import org.skender.testapp.data.local.Restaurant
import org.skender.testapp.injection.module.GlideApp

class RestaurantInjector : SlimInjector<Restaurant> {
    override fun onInject(data: Restaurant, injector: IViewInjector<IViewInjector<*>>) {
        injector.text(R.id.name_restaurant, data.name)
        injector.with(R.id.background_image,  IViewInjector.Action<ImageView>() {
            GlideApp.with(it)
                .load(data.logo)
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(it)
        })
    }
}