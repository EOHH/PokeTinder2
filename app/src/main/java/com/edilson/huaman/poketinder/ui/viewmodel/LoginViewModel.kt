package com.edilson.huaman.poketinder.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edilson.huaman.poketinder.data.model.User
import com.edilson.huaman.poketinder.util.SharedPreferenceUtil

data class LoginViewModel(private val context: Context): ViewModel() {

    public lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    val emptyFieldsError = MutableLiveData<Boolean>()
    val fieldsAuthenticateError = MutableLiveData<Boolean>()
    val goSuccesActivity = MutableLiveData<Boolean>()

    fun onCreate() {
        sharedPreferenceUtil = SharedPreferenceUtil().also {
            it.setSahrePreference(context)
        }
    }

    fun validateInputs(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            emptyFieldsError.postValue(true)
            return
        }
        val user: User? = sharedPreferenceUtil.getUser()

        if (email == user?.email && password == user?.password) {
            goSuccesActivity.postValue(true)
        } else {
            fieldsAuthenticateError.postValue(true)
        }
    }
}
