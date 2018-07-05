package esasydictionary.apirl.com.easydictionary.ui.view.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import esasydictionary.apirl.com.easydictionary.R
import esasydictionary.apirl.com.easydictionary.base.BaseCompatMvpActivity
import esasydictionary.apirl.com.easydictionary.common.Constant
import esasydictionary.apirl.com.easydictionary.di.component.DaggerAPPComponent
import esasydictionary.apirl.com.easydictionary.ext.*
import esasydictionary.apirl.com.easydictionary.ui.contract.ITranslationContract
import esasydictionary.apirl.com.easydictionary.ui.presenter.TranslationPresenter
import esasydictionary.apirl.com.easydictionary.utils.CommonUtils
import esasydictionary.apirl.com.easydictionary.utils.DialogUtils
import kotlinx.android.synthetic.main.activity_translation.*
import org.jetbrains.anko.intentFor


/**
 * 翻译activity
 * Created by april on 2018/7/4.
 */
class TranslationActivity : BaseCompatMvpActivity<TranslationPresenter>(), ITranslationContract.View {


    private var mTranslationState = Constant.TRANSLATION_Z_2_E

    override fun getLayoutRes(): Int {
        return R.layout.activity_translation
    }

    override fun initPerComponent() {
        DaggerAPPComponent.builder()
                .activityComponent(mActivityComponent)
                .build().inject(this)
        mPresenter.bindView(this)
    }

    override fun initAll() {
        super.initAll()
        initViews()
        bindListener()
    }

    //绑定事件
    private fun bindListener() {
        //切换翻译模式
        mImgTrans?.OnClick {
            mImgTrans.RoationAnim()
            switcTranslationState()
        }

        //开始翻译
        mTvTranslationBtn.OnClick {

            if (mEtSource.text.trim().isEmpty()) {
                showTipMsg(resources.getString(R.string.input_translation_hint))
                return@OnClick
            }
            CommonUtils.hideKeyboard(mEtSource, this)
            mPresenter.translation(mEtSource.text.trim().toString(), mTranslationState)
        }


        //编辑文本输入事件
        mEtSource.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.isNotEmpty() == true) {
                    mImgDel.visibility = View.VISIBLE
                } else {
                    mImgDel.visibility = View.INVISIBLE
                }
            }

        })

        //
        mImgDel.OnClick {
            mEtSource.text.clear()
        }

    }

    //初始化view
    private fun initViews() {
        setToolBarTitle(resources.getString(R.string.translation_title))
    }


    //添加一个菜单选项
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(resources.getString(R.string.edit_word_menu_text))
        return true
    }

    //监听菜单选项点击事件
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        GoActivity(intentFor<EditWordActivity>())

        return super.onOptionsItemSelected(item)

    }


    //切换翻译方式
    private fun switcTranslationState() {
        //如果当前状态为 英文-中文
        if (mTranslationState == Constant.TRANSLATION_E_2_Z) {
            //切换橙 中文-英文
            mTranslationState = Constant.TRANSLATION_Z_2_E

            //将翻译源设置为中文
            mTvSourceLanguage?.setText(R.string.chinese_txt)
            mTvSourceLanguage.setLeftDrawables(getDrawableExt(R.drawable.chinese_icon))

            //结果设置为英文
            mTvResultLanguage?.setText(R.string.english_txt)
            mTvResultLanguage.setLeftDrawables(getDrawableExt(R.drawable.english_icon))

        } else {
            //切换 英文-中文
            mTranslationState = Constant.TRANSLATION_E_2_Z
            //将翻译源设置为英文
            mTvSourceLanguage?.setText(R.string.english_txt)
            mTvSourceLanguage.setLeftDrawables(getDrawableExt(R.drawable.english_icon))

            //结果设置为中文
            mTvResultLanguage?.setText(R.string.chinese_txt)
            mTvResultLanguage.setLeftDrawables(getDrawableExt(R.drawable.chinese_icon))

        }
    }


    //翻译结果回调
    override fun onTranslationResult(isOk: Boolean, result: String) {
        mTvResult.text = result
        if (!isOk) {

            DialogUtils.showAlter(this, resources.getString(R.string.no_result_tip), resources.getString(R.string.go_edit_tip), resources.getString(R.string.cancel_edit_tip)
                    , MaterialDialog.SingleButtonCallback { dialog, which ->
                //进入词库录入
                GoActivity(intentFor<EditWordActivity>())
            }
                    , MaterialDialog.SingleButtonCallback { dialog, which ->
                //关闭提示框
                dialog.dismiss()
            })

        }
    }

}