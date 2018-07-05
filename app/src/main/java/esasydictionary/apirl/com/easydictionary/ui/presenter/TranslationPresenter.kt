package esasydictionary.apirl.com.easydictionary.ui.presenter

import esasydictionary.apirl.com.easydictionary.common.Constant
import esasydictionary.apirl.com.easydictionary.data.local.ankosqlite.Word
import esasydictionary.apirl.com.easydictionary.data.local.ankosqlite.database
import esasydictionary.apirl.com.easydictionary.data.local.ankosqlite.parseOpt
import esasydictionary.apirl.com.easydictionary.ui.contract.ITranslationContract
import org.jetbrains.anko.db.select
import javax.inject.Inject

/**
 *
 * Created by april on 2018/7/5.
 */
class TranslationPresenter @Inject constructor() : BasePresenter<ITranslationContract.View>(), ITranslationContract.Presenter {
    override fun translation(text: String, type: Int) {

        var result: String? = null

        when (type) {
            Constant.TRANSLATION_Z_2_E -> result = searchEnglish(text)
            Constant.TRANSLATION_E_2_Z -> result = searchChinese(text)
        }

        mView.onTranslationResult(result?.isNotEmpty() == true, result
                ?: text)
    }


    //搜索英文
    private fun searchEnglish(text: String): String? {
        return mContext.database.use({
            return@use select(Word.Table_name)
                    .whereSimple("${Word.Chinese}=? collate NOCASE ", text)
                    .parseOpt { map: Map<String, Any?> ->
                        map[Word.English] ?: ""
                    }


        })?.toString()
    }

    //搜索中文
    private fun searchChinese(text: String): String? {
        return mContext.database.use({
            return@use select(Word.Table_name)
                    .whereSimple("${Word.English} =? collate NOCASE ", text)

                    .parseOpt { map: Map<String, Any?> ->
                        map[Word.Chinese] ?: ""
                    }


        })?.toString()
    }
}