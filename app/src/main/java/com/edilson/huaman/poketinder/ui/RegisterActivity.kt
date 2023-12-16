package com.edilson.huaman.poketinder.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.edilson.huaman.poketinder.databinding.ActivityRegisterBinding
import com.edilson.huaman.poketinder.ui.viewmodel.RegisterViewModel


class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate) {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Si se realiza click se procede a iniciar el startRegister
        binding.btnRegister.setOnClickListener {
            startRegister()
        }

        registerViewModel = RegisterViewModel(this)

        registerViewModel.onCreate()

        registerViewModel.emptyFieldsError.observe(this) {
            Toast.makeText(this, "Completar todos los campos", Toast.LENGTH_SHORT).show()
        }

        // Verificando si las contraseñas coinciden para la creacion de usuario
        registerViewModel.fieldsPasswordIquals.observe(this) {
            Toast.makeText(this, "Las contraseñas no coinciden. Por favor," +
                    " inténtelo de nuevo!", Toast.LENGTH_SHORT).show()
        }
        // Si coinciden se procede a registrar el usuario
        registerViewModel.goSuccessRegister.observe(this) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        // Boton de login
        binding.btnGoLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
    // Recogiendo la data ingresada por el usuario
    private  fun startRegister() {
        registerViewModel.validateInputs(
            userId = "1",
            binding.edtUserName.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString(),
            binding.edtPassword2.text.toString()
        )
    }
}