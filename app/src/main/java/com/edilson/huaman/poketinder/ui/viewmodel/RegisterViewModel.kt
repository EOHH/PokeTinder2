package com.edilson.huaman.poketinder.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edilson.huaman.poketinder.data.model.User
import com.edilson.huaman.poketinder.util.SharedPreferenceUtil

class RegisterViewModel(private val context: Context) : ViewModel() {

    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    // Definiendo variables para la verificacion
    val fieldsPasswordIquals = MutableLiveData<Boolean>()
    val goSuccessRegister = MutableLiveData<Boolean>()
    val emptyFieldsError = MutableLiveData<Boolean>()

    fun onCreate() {
        // Instanciando la clase sharedprederencesUtil
        sharedPreferenceUtil = SharedPreferenceUtil().also {
            it.setSahrePreference(context)
        }
    }
    // Validando los campos de password y password2
    fun validateInputs(userId: String, userName:String, email: String, password: String, password2: String ) {
        if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
            emptyFieldsError.postValue(true)
            return
        }
        if (password == password2) {
            val user = User(
                userId,
                userName,
                email,
                password
            )
            // Se guardan los datos | registrar usuario
            sharedPreferenceUtil.saveUser(user)
            // Permite el registro
            goSuccessRegister.postValue(true)
        } else {
            // Muestra un mensaje de error
            fieldsPasswordIquals.postValue(true)
        }
    }
}