package com.jiayiworld.baselibrary.di.module

import android.app.Activity
import com.kotlin.base.injection.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by april on 2018/1/10.
 */

@Module
class ActivityModule(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun providerActivity(): Activity {
        return this.activity
    }
}