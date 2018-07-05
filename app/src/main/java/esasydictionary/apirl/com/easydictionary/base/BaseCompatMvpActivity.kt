package esasydictionary.apirl.com.easydictionary.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.jiayiworld.baselibrary.di.component.ActivityComponent
import com.jiayiworld.baselibrary.di.component.DaggerActivityComponent
import com.jiayiworld.baselibrary.di.module.ActivityModule
import esasydictionary.apirl.com.easydictionary.ui.presenter.BasePresenter
import esasydictionary.apirl.com.easydictionary.ui.presenter.base.IView
import esasydictionary.apirl.com.easydictionary.utils.ToastUtil
import javax.inject.Inject

/**
 * Created by april on 2018/7/4.
 */
abstract class BaseCompatMvpActivity<P : BasePresenter<*>> : BaseAppCompatActivity(), IView {
    @Inject
    lateinit var mPresenter: P

    lateinit var mActivityComponent: ActivityComponent


    override fun initAll() {
        super.initAll()
        initActivityComponent()
        initPerComponent()


        regtesterBroadcastReceiver()
        mPresenter.subscribe()

    }

    override fun onDestroy() {
        super.onDestroy()

        unRegtesterBroadcastReceiver()

        mPresenter.unsubscribe()

    }

    private fun initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .build()
    }

    abstract fun initPerComponent()


    /**
     * 获取广播 actions 子类可重写，返回对应的action，
     *
     * @return
     */
    open protected fun getReceiverActions(): Array<String>? {
        return null
    }

    private fun regtesterBroadcastReceiver() {


        if (getReceiverActions() == null || getReceiverActions()!!.isEmpty()) {
            return
        }

        try {


            val filter = IntentFilter()
            getReceiverActions()!!.forEach {
                filter.addAction(it)
            }


            registerReceiver(UniteBroadcastReceiver, filter)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun unRegtesterBroadcastReceiver() {
        if (getReceiverActions() == null || getReceiverActions()!!.isEmpty()) {
            return
        }
        try {
            unregisterReceiver(UniteBroadcastReceiver)
        } catch (ignored: Exception) {
        }

    }


    /**
     * 广播回调 子类可重写处理逻辑
     *
     * @param intent
     */
    open protected fun onReceiverCallback(intent: Intent) {}

    private val UniteBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if (intent == null) {
                return
            }
            onReceiverCallback(intent)
        }
    }

    override fun showTipMsg(msg: String) {
        ToastUtil.showToast(this, msg)
    }


}