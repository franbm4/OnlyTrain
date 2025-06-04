package com.example.myapplicationtlg.ui

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.myapplicationtlg.api.repository.AuthRespository
import com.example.myapplicationtlg.databinding.ActivityRegisterBinding
import kotlinx.coroutines.launch


class RegisterActivity : BaseActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val auth = AuthRespository()

        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etPassword.text.toString()
            val confirm = binding.etConfirmPassword.text.toString()

            if (email.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pass != confirm) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            lifecycleScope.launch {
                auth.registerUser(email, pass)

                val success = auth.registerUser(email, pass)
                if (success) {
                    Toast.makeText(this@RegisterActivity, "Ser ha enviado un email a ${email}", Toast.LENGTH_SHORT).show()
                    // Ir al siguiente Activity o guardar sesión localmente
                } else {
                    Toast.makeText(this@RegisterActivity, "Error al registrarse", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}