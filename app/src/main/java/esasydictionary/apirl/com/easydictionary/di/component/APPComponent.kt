package esasydictionary.apirl.com.easydictionary.di.component

import com.jiayiworld.baselibrary.di.component.ActivityComponent
import com.kotlin.base.injection.PerComponentScope
import dagger.Component
import esasydictionary.apirl.com.easydictionary.ui.view.activity.EditWordActivity
import esasydictionary.apirl.com.easydictionary.ui.view.activity.TranslationActivity

/**
 * Created by april on 2018/7/4.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class))
interface APPComponent {
    fun inject(activity: EditWordActivity)
    fun inject(activity: TranslationActivity)
}