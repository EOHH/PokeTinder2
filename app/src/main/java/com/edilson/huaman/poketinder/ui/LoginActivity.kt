package com.edilson.huaman.poketinder.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.edilson.huaman.poketinder.data.model.User
import com.edilson.huaman.poketinder.databinding.ActivityLoginBinding
import com.edilson.huaman.poketinder.ui.viewmodel.LoginViewModel
import com.edilson.huaman.poketinder.util.SharedPreferenceUtil

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnLogin.setOnClickListener {
            startLogin()
        }

        loginViewModel = LoginViewModel(this)

        loginViewModel.onCreate()

        loginViewModel.emptyFieldsError.observe(this) {
            Toast.makeText(this, "Ingrese los datos de usuario", Toast.LENGTH_SHORT).show()
        }

        loginViewModel.fieldsAuthenticateError.observe(this) {
            Toast.makeText(this, "Error usuario", Toast.LENGTH_SHORT).show()
        }

        loginViewModel.goSuccesActivity.observe(this) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    private fun startLogin() {
        loginViewModel.validateInputs(
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString()
        )
    }
}