package com.jiayiworld.baselibrary.di.component

import android.app.Activity
import android.content.Context
import com.jiayiworld.baselibrary.di.module.ActivityModule
import com.kotlin.base.injection.ActivityScope
import dagger.Component

/**
 * Created by april on 2018/1/10.
 */
@ActivityScope
@Component(
        dependencies = arrayOf(ApplicationComponent::class)
        , modules = arrayOf(ActivityModule::class)
)
interface ActivityComponent {

    fun activity(): Activity
    fun context(): Context

}