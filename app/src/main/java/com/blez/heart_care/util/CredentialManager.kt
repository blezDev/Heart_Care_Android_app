package com.blez.heart_care.util

import android.content.Context
import android.content.SharedPreferences
import com.blez.heart_care.util.Constants.PREFS_TOKEN_FILE

class CredentialManager(private val context: Context) {
    private var prefs : SharedPreferences = context.getSharedPreferences(PREFS_TOKEN_FILE,Context.MODE_PRIVATE)


    fun saveUsername(username : String){
        val editor = prefs.edit()
        editor.putString(Constants.USER_NAME,username)
        editor.apply()
    }

    fun getUsername() : String?{
        return prefs.getString(Constants.USER_NAME,null)
    }

    fun saveToken(token : String)
    {
        val editor = prefs.edit()
        editor.putString(Constants.USER_TOKEN,token)
        editor.apply()

    }
    fun getToken() : String?{
        return prefs.getString(Constants.USER_TOKEN,null)
    }

    fun deteleCredit(){
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
    fun savePic(pic : String){
        val editor = prefs.edit()
        editor.putString(Constants.USER_PIC,pic)
        editor.apply()
    }
    fun getPic() : String?{
        return prefs.getString(Constants.USER_PIC,null)
    }


}