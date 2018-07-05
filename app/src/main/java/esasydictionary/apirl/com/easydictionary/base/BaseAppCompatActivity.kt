package esasydictionary.apirl.com.easydictionary.base

import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.transition.TransitionInflater
import android.view.Window
import android.widget.TextView
import esasydictionary.apirl.com.easydictionary.R
import esasydictionary.apirl.com.easydictionary.utils.ToastUtil

/**
 * activity 父类，统一操作
 */
abstract class BaseAppCompatActivity : AppCompatActivity() {

    abstract fun getLayoutRes(): Int

    private var mToolbarTitle: TextView? = null
    private var mToolbarSubTitle: TextView? = null
    private var mToolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initSwitchActivityAnimation()

        if (getLayoutRes() > 0) {
            setContentView(getLayoutRes())
        }
        initAll()


    }



    /**
     * 初始化activity 切换动画
     */
    private fun initSwitchActivityAnimation() {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val explode = TransitionInflater.from(this).inflateTransition(R.transition.explode)
            //退出时使用
            window.exitTransition = explode

            window.enterTransition = explode

            window.reenterTransition = explode
        }
    }

    open protected fun initAll() {
        initToolbar()
    }


    private fun initToolbar() {

        mToolbar = findViewById(R.id.toolbar)
        if (getToolbarColor() != -1) {
            mToolbar?.setBackgroundResource(getToolbarColor())
        }

        mToolbarTitle = findViewById(R.id.toolbar_title)
        mToolbarSubTitle = findViewById(R.id.toolbar_subtitle)
        mToolbar?.let {
            //将Toolbar显示到界面
            setSupportActionBar(mToolbar)
            supportActionBar!!.setDisplayShowTitleEnabled(false)
        }

        //getTitle()的值是activity的android:lable属性值
        mToolbarTitle?.text = title
        //设置默认的标题不显示
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }


    open fun getToolbarColor(): Int {
        return -1
    }


    override fun onStart() {
        super.onStart()
        /**
         * 判断是否有Toolbar,并默认显示返回按钮
         */
        if (null != getToolbar() && isShowBacking()) {
            showBack()
        }
    }

    /**
     * 获取头部标题的TextView
     *
     * @return
     */
    fun getToolbarTitle(): TextView? {
        return mToolbarTitle
    }

    /**
     * 获取头部标题的TextView
     *
     * @return
     */
    fun getSubTitle(): TextView? {
        return mToolbarSubTitle
    }


    /**
     * 设置头部标题
     *
     * @param title
     */
    fun setToolBarTitle(title: String) {


        if (mToolbarTitle != null) {

            mToolbarTitle!!.text = title
        } else {


            getToolbar()?.title = title
            setSupportActionBar(getToolbar())
        }
    }

    /**
     * this Activity of tool bar.
     * 获取头部.
     *
     * @return support.v7.widget.Toolbar.
     */
    fun getToolbar(): Toolbar? {
        return mToolbar
    }


    /**
     * 版本号小于21的后退按钮图片
     */
    private fun showBack() {
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        getToolbar()?.setNavigationIcon(R.mipmap.back_icon)
        getToolbar()?.setNavigationOnClickListener({
            if (backClick()) {
                onBackPressed()
            }
        })
    }

    /**
     * 回退按钮点击事件，可由子类拦截处理其他逻辑
     * 返回true 表示父类可以继续执行后续逻辑（即退出activity）
     * 返回false 表示子类完全拦截，父类将不会执行原先后续的逻辑
     */
    open protected fun backClick(): Boolean {
        return true
    }


    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     *
     * @return
     */
    open protected fun isShowBacking(): Boolean {
        return true
    }

    protected val mProgressMsgDialog: ProgressDialog by lazy {
        ProgressDialog(this)
    }


}