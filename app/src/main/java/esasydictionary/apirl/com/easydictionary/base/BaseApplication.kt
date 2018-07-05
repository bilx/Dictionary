package esasydictionary.apirl.com.easydictionary.base

import android.app.Application
import android.content.Context
import com.jiayiworld.baselibrary.di.component.ApplicationComponent
import com.jiayiworld.baselibrary.di.component.DaggerApplicationComponent
import com.jiayiworld.baselibrary.di.module.ApplicaionModule


/**
 *
 */
open class BaseApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    companion object {

        lateinit var instance: BaseApplication

        lateinit var context: Context

    }

    override fun onCreate() {
        super.onCreate()

        context = this
        instance = this

        //初始化 applicationComponent
        initAppComponent()

    }


    //初始化appComponent
    private fun initAppComponent() {

        appComponent = DaggerApplicationComponent.builder()
                .applicaionModule(ApplicaionModule(this))
                .build()
    }


}