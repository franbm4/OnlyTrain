package com.example.myapplicationtlg.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.myapplicationtlg.api.repository.AuthRespository
import com.example.myapplicationtlg.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val auth = AuthRespository()

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etPassword.text.toString()
            lifecycleScope.launch {
                val success = auth.loginUser(email, pass)
                if (success) {
                    Toast.makeText(this@LoginActivity, "Sesión iniciada", Toast.LENGTH_SHORT).show()
                    // Ir al siguiente Activity o guardar sesión localmente
                } else {
                    Toast.makeText(this@LoginActivity, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.tvGoToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}