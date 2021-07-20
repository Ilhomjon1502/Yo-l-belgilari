package DB

import Models.Belgi
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context: Context?)
    :SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION), DbService{

    companion object{
        const val DB_NAME = "belgi_db"
        const val DB_VERSION = 1

        const val TABLE_NAME_OGOH = "table_ogohlantiruvchi"
        const val ID_OGOH = "id"
        const val NAME_OGOH = "name"
        const val MATN_OGOH = "matn"
        const val IMAGE_OGOH = "image_path"
        const val LIKE_OGOH = "like"
        const val CATEGORY = "category"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val queryOgogh = "create table $TABLE_NAME_OGOH ($ID_OGOH integer not null primary key autoincrement unique, $NAME_OGOH text not null, $MATN_OGOH text not null, $IMAGE_OGOH text not null, $LIKE_OGOH integer not null, $CATEGORY integer not null)"

        db?.execSQL(queryOgogh)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


    override fun addLabel(belgi: Belgi) {
        val dataBase = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(NAME_OGOH, belgi.name)
        contentValue.put(MATN_OGOH, belgi.matni)
        contentValue.put(IMAGE_OGOH, belgi.imagePath)
        contentValue.put(LIKE_OGOH, belgi.like)
        contentValue.put(CATEGORY, belgi.category)
        dataBase.insert(TABLE_NAME_OGOH, null, contentValue)
        dataBase.close()
    }

    override fun editLabel(belgi: Belgi): Int {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(NAME_OGOH, belgi.name)
        contentValue.put(MATN_OGOH, belgi.matni)
        contentValue.put(IMAGE_OGOH, belgi.imagePath)
        contentValue.put(LIKE_OGOH, belgi.like)
        contentValue.put(CATEGORY, belgi.category)

        return database.update(TABLE_NAME_OGOH, contentValue, "$ID_OGOH = ?", arrayOf(belgi.id.toString()))
    }

    override fun deleteLabel(belgi: Belgi) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME_OGOH, "$ID_OGOH = ?", arrayOf("${belgi.id}"))
        database.close()
    }

    override fun getAllLabel(): ArrayList<Belgi> {
        val list = ArrayList<Belgi>()
        val query = "select * from $TABLE_NAME_OGOH"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()){
            do{
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val matni = cursor.getString(2)
                val imagePath = cursor.getString(3)
                val like = cursor.getInt(4)
                val category = cursor.getInt(5)
                val contact = Belgi(id, name, matni, imagePath, like, category)
                list.add(contact)
            }while (cursor.moveToNext())
        }
        return list
    }
}