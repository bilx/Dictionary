package esasydictionary.apirl.com.easydictionary.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by april on 2018/7/4.
 */
object ToastUtil {

    fun showToast(context: Context, msg: String?) {
        if (msg == null || msg.trim().isEmpty()) {
            return
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showToast(context: Context, msgRes: Int) {
        Toast.makeText(context, msgRes, Toast.LENGTH_SHORT).show()
    }
}