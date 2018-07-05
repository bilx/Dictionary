package com.jiayiworld.baselibrary.di.component

import android.content.Context
import com.jiayiworld.baselibrary.di.module.ApplicaionModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by april on 2018/1/10.
 */
@Singleton
@Component(
        modules = arrayOf(ApplicaionModule::class)
)
interface ApplicationComponent {
    fun context(): Context
}