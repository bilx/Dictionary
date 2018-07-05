package esasydictionary.apirl.com.easydictionary.ui.presenter

import android.util.Log
import esasydictionary.apirl.com.easydictionary.data.local.ankosqlite.Word
import esasydictionary.apirl.com.easydictionary.data.local.ankosqlite.database
import esasydictionary.apirl.com.easydictionary.ui.contract.IEditWordContract
import esasydictionary.apirl.com.easydictionary.utils.DateUtils
import org.jetbrains.anko.db.insert
import javax.inject.Inject

/**
 * Created by april on 2018/7/4.
 */
class EditWordPresenter @Inject constructor() : BasePresenter<IEditWordContract.View>(), IEditWordContract.Presenter {

    override fun saveWord(chinese: String, english: String) {
        mContext.database.use {
            val result = insert(Word.Table_name,
                    Word.Chinese to chinese,
                    Word.English to english,
                    Word.CreateTime to DateUtils.curTime.toString())
            Log.d("11", "result = $result")

            mView.onSaveResult(true, "")

        }
    }
}