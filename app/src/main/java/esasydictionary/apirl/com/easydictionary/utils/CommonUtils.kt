package esasydictionary.apirl.com.easydictionary.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Created by april on 2018/7/5.
 */
object CommonUtils {
    //隐藏软键盘
    fun hideKeyboard(view: EditText, context: Context) {
        view.clearFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}