package esasydictionary.apirl.com.easydictionary.ui.view.activity

import esasydictionary.apirl.com.easydictionary.R
import esasydictionary.apirl.com.easydictionary.base.BaseCompatMvpActivity
import esasydictionary.apirl.com.easydictionary.di.component.DaggerAPPComponent
import esasydictionary.apirl.com.easydictionary.ext.OnClick
import esasydictionary.apirl.com.easydictionary.ui.contract.IEditWordContract
import esasydictionary.apirl.com.easydictionary.ui.presenter.EditWordPresenter
import kotlinx.android.synthetic.main.activity_edit_word.*

/**
 * Created by april on 2018/7/4.
 */
class EditWordActivity : BaseCompatMvpActivity<EditWordPresenter>(), IEditWordContract.View {


    override fun initPerComponent() {
        DaggerAPPComponent.builder()
                .activityComponent(mActivityComponent)
                .build().inject(this)
        mPresenter.bindView(this)
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_edit_word
    }

    override fun initAll() {
        super.initAll()
        initViews()

        bindListener()
    }


    private fun initViews() {
        setToolBarTitle(resources.getString(R.string.edit_word_title))
    }


    private fun bindListener() {
        mTvSaveBtn.OnClick {
            saveData()
        }
    }

    //保存数据
    private fun saveData() {
        val chinese = mEtChinese.text.toString()
        val english = mEtEnglish.text.toString()

        if (chinese.trim().isEmpty()) {
            showTipMsg(resources.getString(R.string.input_chinese_tip))
            return
        }

        if (english.trim().isEmpty()) {
            showTipMsg(resources.getString(R.string.input_english_tip))
            return
        }
        mPresenter.saveWord(chinese, english)
    }

    //保存数据回调
    override fun onSaveResult(isOk: Boolean, message: String) {
        if (isOk) {
            showTipMsg(resources.getString(R.string.success))
            mEtChinese.text.clear()
            mEtEnglish.text.clear()
        } else {
            showTipMsg(resources.getString(R.string.fail))
        }
    }


}