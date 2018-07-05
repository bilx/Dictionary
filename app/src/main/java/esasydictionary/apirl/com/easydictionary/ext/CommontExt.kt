package esasydictionary.apirl.com.easydictionary.ext

import android.animation.ObjectAnimator
import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.TextView

/**
 * 常用扩展函数
 * Created by april on 2018/7/4.
 */

/**
 *  View 点击事件设置
 */
fun View.OnClick(method: () -> Unit) {
    this.setOnClickListener({ method() })
}


/**
 * 添加activity 跳转动画
 */
fun Activity.GoActivity(intent: Intent) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    } else {
        startActivity(intent)
    }
}

//获取drawable
fun Context.getDrawableExt(res: Int): Drawable {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        resources.getDrawable(res, theme)
    } else {
        resources.getDrawable(res)
    }
}

//设置textview左侧drawable
fun TextView.setLeftDrawables(leftDrawable: Drawable) {

    /// 这一步必须要做,否则不会显示.
    leftDrawable.setBounds(0, 0, leftDrawable.minimumWidth, leftDrawable.minimumHeight);
    setCompoundDrawables(leftDrawable, null, null, null)

}

/**
 * 扩展，view添加一个旋转动画，
 * @param duration 动画执行时间， 默认为1秒钟
 */
fun View.RoationAnim(duration: Long = 1000) {
    //逆时针旋转
    val anim = ObjectAnimator.ofFloat(this, "rotation", 360f, 0f)
    //动画时间
    anim.duration = duration
    //执行动画
    anim.start()
}