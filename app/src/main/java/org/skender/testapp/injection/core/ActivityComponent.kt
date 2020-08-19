package org.skender.testapp.injection.core

import dagger.Component
import org.skender.testapp.injection.core.AppComponent
import org.skender.testapp.injection.module.ActivityModule
import org.skender.testapp.injection.module.PerActivity
import org.skender.testapp.ui.base.AbstractActivity

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun activity(): AbstractActivity
}