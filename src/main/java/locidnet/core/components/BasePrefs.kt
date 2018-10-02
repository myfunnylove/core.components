package locidnet.core.components

import android.content.SharedPreferences

class BasePrefs(private val sharedPreferences: SharedPreferences) {



    companion object {
        var prefs : BasePrefs? = null
        fun instance(sharedPreferences: SharedPreferences) : BasePrefs{
            if (prefs == null)  prefs = BasePrefs(sharedPreferences)
            return prefs!!
        }

    }

    fun setData(key : String, value : String){
        sharedPreferences.edit().run {
            putString(key,value)
            apply()
        }

    }

    fun setData(key : String, value : Int){
        sharedPreferences.edit().run {
            putInt(key,value)
            apply()
        }

    }
    fun setData(key : String, value : Long){
        sharedPreferences.edit().run {
            putLong(key,value)
            apply()
        }

    }

    fun setData(key : String, value : Boolean){
        sharedPreferences.edit().run {
            putBoolean(key,value)
            apply()
        }

    }

    fun getData(key : String,def : String) : String {
       return sharedPreferences.getString(key, def)
    }

    fun getData(key : String,def : Int) : Int {
        return sharedPreferences.getInt(key, def)
    }

    fun getData(key : String,def : Long) : Long {
        return sharedPreferences.getLong(key, def)
    }
    fun getData(key : String,def : Boolean) : Boolean {
        return sharedPreferences.getBoolean(key, def)
    }




}