package com.jiayiworld.baselibrary.di.module

import android.content.Context

import dagger.Module
import dagger.Provides
import esasydictionary.apirl.com.easydictionary.base.BaseApplication
import javax.inject.Singleton

/**
 * Created by april on 2018/1/10.
 */
@Module
class ApplicaionModule(private val context: BaseApplication) {

    @Singleton
    @Provides
    fun providerContext(): Context {
        return this.context
    }
}