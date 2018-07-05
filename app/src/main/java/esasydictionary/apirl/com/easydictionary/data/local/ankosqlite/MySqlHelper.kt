package esasydictionary.apirl.com.easydictionary.data.local.ankosqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import esasydictionary.apirl.com.easydictionary.base.BaseApplication
import org.jetbrains.anko.db.*

/**
 *  数据库帮助类 （创建数据库文件，数据表）
 * Created by april on 2018/7/4.
 */
class MySqlHelper(context: Context = BaseApplication.instance) : ManagedSQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        val DB_NAME = "dictionary.db"
        val DB_VERSION = 1
        val instance by lazy { MySqlHelper() }
    }


    override fun onCreate(db: SQLiteDatabase?) {

        //创建词语表
        db?.createTable(Word.Table_name, true,
                Word.Id to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Word.Chinese to TEXT ,
                Word.English to TEXT,
                Word.CreateTime to TEXT)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //删除表重建
        db?.dropTable(Word.Table_name, true)
        onCreate(db)
    }
}

// 扩展函数，Context 可以直接使用database
val Context.database: MySqlHelper
    get() = MySqlHelper.instance