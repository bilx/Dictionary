package esasydictionary.apirl.com.easydictionary.utils

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import esasydictionary.apirl.com.easydictionary.R

/**
 * Created by april on 2018/7/5.
 */
object DialogUtils {

    fun showAlter(context: Context, content: String, positiveText: String, negativeText: String, onPositiveListener: MaterialDialog.SingleButtonCallback
                  , onNegativeListener: MaterialDialog.SingleButtonCallback) {
        MaterialDialog.Builder(context)
                .title(context.resources.getString(R.string.tip))
                .content(content)
                .positiveText(positiveText)
                .negativeText(negativeText)
                .onPositive(onPositiveListener)
                .onNegative(onNegativeListener)
                .show()
    }
}