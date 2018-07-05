package esasydictionary.apirl.com.easydictionary.ui.presenter

import android.content.Context
import esasydictionary.apirl.com.easydictionary.ui.presenter.base.IView
import javax.inject.Inject

/**
 * presenter 父类 （mvp分层模式）
 * Created by april on 2018/7/4.
 */
open class BasePresenter<V : IView> {

    @Inject
    lateinit var mContext: Context

    protected lateinit var mView: V

    fun bindView(view: V) {
        this.mView = view
    }


    open fun subscribe() {

    }

    open fun unsubscribe() {

    }

}