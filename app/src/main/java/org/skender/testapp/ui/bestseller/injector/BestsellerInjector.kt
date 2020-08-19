package org.skender.testapp.ui.bestseller.injector

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import net.idik.lib.slimadapter.SlimInjector
import net.idik.lib.slimadapter.viewinjector.IViewInjector
import org.skender.testapp.R
import org.skender.testapp.data.local.Bestseller
import org.skender.testapp.injection.module.GlideApp

class BestsellerInjector constructor(private val listenerLongClick: BestsellerLongClickListener): SlimInjector<Bestseller> {
    override fun onInject(data: Bestseller, injector: IViewInjector<IViewInjector<*>>) {
        injector.text(R.id.name_dish, data.name)
        injector.text(R.id.description, data.description)
        injector.with(R.id.background_image,  IViewInjector.Action<ImageView>() {
            GlideApp.with(it)
                .load(data.image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        injector.longClicked(R.id.root_item,null)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        injector.longClicked(R.id.root_item) {
                                data.image?.let { it1 -> listenerLongClick.longClick(it1) }
                                return@longClicked true
                        }
                        return false
                    }
                }).into(it)
        })

    }

    interface BestsellerLongClickListener{
        fun longClick(url: String)
    }
}