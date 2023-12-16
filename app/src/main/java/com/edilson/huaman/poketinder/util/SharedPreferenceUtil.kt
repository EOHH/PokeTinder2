package com.edilson.huaman.poketinder.util


import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.edilson.huaman.poketinder.data.model.User

class SharedPreferenceUtil {

    companion object {
        private const val SHARED_PREFERENCE_KEY = "SHARED_PREFERENCE_KEY"

        private lateinit var sharedPreference: SharedPreferences

        private const val USER = "USER_KEY"

    }

    fun setSahrePreference(context: Context) {
        sharedPreference = context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
    }
    // Convirtiendo de un objeto a tipo Json

    fun saveUser(user: User) {
        val gson = Gson()

        val jsonUser = gson.toJson(user)

        sharedPreference
            .edit()
            .putString(USER, jsonUser)
            .apply()
    }
    // Recogiendo el dato Json a objeto || Json == Objeto

    fun getUser(): User? {
        val gson = Gson()

        var user: User? = null

        val jsonUser = sharedPreference.getString(USER, "")

        user = gson.fromJson(jsonUser, User::class.java)

        return user
    }

    // Eliminando datos del user
    fun clearUser() {
        sharedPreference
            .edit()
            .remove(USER)
            .apply()
    }
}