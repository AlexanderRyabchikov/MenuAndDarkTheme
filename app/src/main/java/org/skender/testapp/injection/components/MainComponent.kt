package org.skender.testapp.injection.components

import dagger.Component
import org.skender.testapp.injection.core.ActivityComponent
import org.skender.testapp.injection.core.AppComponent
import org.skender.testapp.injection.module.ActivityModule
import org.skender.testapp.injection.module.PerActivity
import org.skender.testapp.ui.bestseller.BestsellerFragment
import org.skender.testapp.ui.feedback.FeedbackFragment
import org.skender.testapp.ui.main.MainActivity
import org.skender.testapp.ui.restaurant.RestaurantFragment
import org.skender.testapp.ui.splash.SplashScreen

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface MainComponent : ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: SplashScreen)

    fun inject(fragment: FeedbackFragment)

    fun inject (fragment: BestsellerFragment)

    fun inject (fragment: RestaurantFragment)
}