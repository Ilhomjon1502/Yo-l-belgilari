package Kesh

import Models.Belgi
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MySharedPreference {
    private const val NAME = "KeshXotiraga"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context?) {
        preferences = context?.getSharedPreferences(NAME, MODE)!!
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var obektString: ArrayList<Belgi>
        get() = gsonStringToArray(preferences.getString("obekt", "[]")!!)
        set(value) = preferences.edit {
            if (value != null) {
                it.putString("obekt", arrayToGsonString(value))
            }
        }

    fun arrayToGsonString(arrayList: ArrayList<Belgi>): String {
        return Gson().toJson(arrayList)
    }

    fun gsonStringToArray(gsonString: String): ArrayList<Belgi> {
        val typeToken = object : TypeToken<ArrayList<Belgi>>() {}.type
        return Gson().fromJson(gsonString, typeToken)
    }
}