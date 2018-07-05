package esasydictionary.apirl.com.easydictionary.ui.contract

import esasydictionary.apirl.com.easydictionary.ui.presenter.base.IView

/**
 * Created by april on 2018/7/4.
 */
interface IEditWordContract {

    interface View : IView {
        fun onSaveResult(isOk: Boolean, message: String)
    }

    interface Presenter {
        fun saveWord(chinese: String, english: String)
    }
}