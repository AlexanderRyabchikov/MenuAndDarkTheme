package org.skender.testapp.injection.module

import dagger.Module
import dagger.Provides
import org.skender.testapp.ui.base.AbstractActivity

@Module
class ActivityModule(private var activity: AbstractActivity) {

    @Provides
    @PerActivity
    fun provideActivity(): AbstractActivity = activity

}