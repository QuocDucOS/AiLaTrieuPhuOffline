package com.example.altp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Environment
import android.util.Log
import com.example.altp.data.DataPerson
import com.example.altp.data.DataQuestion
import java.io.File
import java.io.FileOutputStream

class GetSQLData {
    private var context: Context
    private var database: SQLiteDatabase? = null

    constructor(context: Context) {
        this.context = context
        copyData()
        createtable()

    }

    fun copyData() {
        var data =
            Environment.getDataDirectory().path + "/data/" + context.packageName + "/latrieuphu"
        if (File(data).exists()) {
            return
        }
        var asest = context.assets
        var input = asest.open("latrieuphu")

        var output = FileOutputStream(data)
        var byte = ByteArray(1024)
        var l = input.read(byte)
        while (l > 0) {
            output.write(byte, 0, l)
            l = input.read(byte)
        }
        output.close()
        input.close()
    }

    private fun createtable() {
        val sql = "CREATE TABLE IF NOT EXISTS hight_score " +
                "(id INTEGER NOT NULL, " +
                "name TEXT, " +
                "levelpass TEXT, " +
                "money TEXT, " +
                "PRIMARY KEY(id AUTOINCREMENT)" +
                ")"
        openData()
        database!!.execSQL(sql)
        closeData()
    }

    fun openData() {
        if (database != null && database!!.isOpen) {
            return
        }
        var data =
            Environment.getDataDirectory().path + "/data/" + context.packageName + "/latrieuphu"
        database = SQLiteDatabase.openDatabase(data, null, SQLiteDatabase.OPEN_READWRITE)

    }


    fun closeData() {
        if (database != null && database!!.isOpen) {
            database!!.close()
            database = null
        }
    }

    fun getDataQuestion(): MutableList<DataQuestion> {
        var listqs = mutableListOf<DataQuestion>()
        val sql = "select * from (select * from Question order by random()) as t " +
                "GROUP by level order by level"
        openData()
        var cr = database!!.rawQuery(sql, null, null)
        var number = cr.getColumnIndex("level")
        var question = cr.getColumnIndex("question")
        var qsA = cr.getColumnIndex("casea")
        //  var qsAa = cr.getColumnIndex(MediaStore.Video.Media.TITLE)
        var qsB = cr.getColumnIndex("caseb")
        var qsC = cr.getColumnIndex("casec")
        var qsD = cr.getColumnIndex("cased")
        var truecase = cr.getColumnIndex("truecase")
        cr.moveToFirst()
        while (!cr.isAfterLast) {
            var nb = cr.getInt(number)
            var qs = cr.getString(question)
            var qsa = cr.getString(qsA)
            var qsb = cr.getString(qsB)
            var qsc = cr.getString(qsC)
            var qsd = cr.getString(qsD)
            var istrue = cr.getInt(truecase)
            listqs.add(
                DataQuestion(
                    nb,
                    qs,
                    qsa,
                    qsb,
                    qsc,
                    qsd,
                    istrue
                )
            )
            cr.moveToNext()
            Log.d("hello", "number: " + nb)
            Log.d("hello", "question: " + qs)
            Log.d("hello", "qsa: " + qsa)
            Log.d("hello", "qsb: " + qsb)
            Log.d("hello", "qsc: " + qsc)
            Log.d("hello", "qsd: " + qsd)
            Log.d("hello", "truecase: " + istrue)
            Log.d("hello", "=================================")
        }
        closeData()
        return listqs
    }

    fun insertHight(person: DataPerson) {

        val content = ContentValues()

        content.put("name", person.name)
        content.put("level_pass", person.level)
        content.put("money", person.money)
        openData()
        database!!.insert("hight_score", null, content)
        closeData()
    }

    fun getAll(): MutableList<DataPerson> {
        val db: String = "select * from hight_score"
        openData()
        var cr = database!!.rawQuery(db, null, null)
        cr.moveToFirst()

        var name = cr.getColumnIndex("name")
        var id = cr.getColumnIndex("id")
        var money = cr.getColumnIndex("level_pass")
        var level = cr.getColumnIndex("money")
        var dta = mutableListOf<DataPerson>()
        while (!cr.isAfterLast) {
            var i = cr.getInt(id)
            var n = cr.getString(name)
            var mon = cr.getString(money)
            var lev = cr.getString(level)
            dta.add(DataPerson(i.toString(), n, lev, mon))
            cr.moveToNext()
        }
        closeData()
        return dta
    }

    fun deleteUser(person: DataPerson): Int {
        openData()
        return database!!.delete("hight_score", "id = ?", arrayOf(person.id))
        closeData()
    }

    fun insertUpdate(id: Int, level: Int, money: String) {
        //ContentValues: la key-value
        val content = ContentValues()
        content.put("level_pass", level)
        content.put("money", money)
        openData()
        database!!.update(
            "hight_score", content,
            "id = " + id.toString(), null
        )
        closeData()
    }
}