package esasydictionary.apirl.com.easydictionary.ui.contract

import esasydictionary.apirl.com.easydictionary.ui.presenter.base.IView

/**
 * Created by april on 2018/7/5.
 */
interface ITranslationContract {
    interface View : IView {
        fun onTranslationResult(isOk: Boolean, result: String)
    }

    interface Presenter {
        fun translation(text: String, type: Int)
    }
}