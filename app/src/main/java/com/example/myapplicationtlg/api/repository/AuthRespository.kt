package com.example.myapplicationtlg.api.repository

import android.util.Log
import com.example.myapplicationtlg.api.Client
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email


class AuthRespository {
    suspend fun loginUser(email: String, password: String): Boolean {
        val supabase = Client.getInstance()
        return try {
            val session = supabase.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            val user = supabase.auth.currentUserOrNull()
            Log.d("SupabaseAuth", "Inicio de sesión exitoso: ${user?.email}")
            true
        } catch (e: Exception) {
            Log.e("SupabaseAuth", "Error en login: ${e.localizedMessage}")
            false
        }
    }
    suspend fun registerUser(registerEmail: String, registerPassword: String): Boolean {
        return try {
            val result = Client.getInstance().auth.signUpWith(Email) {
                email = registerEmail
                password = registerPassword
            }
            //Log.d("SupabaseAuth", "Usuario registrado con ID: ${result.user?.id}")
            true
        } catch (e: Exception) {
            Log.e("SupabaseAuth", "Error registrando: ${e.message}")
            false
        }
    }


    suspend fun isUserLogedIn (): Boolean {
        val client = Client.getInstance()

        // ✅ Esperamos a que Supabase termine de restaurar la sesión (si existe)
        client.auth.awaitInitialization()
        val currentUser = Client.getInstance().auth.currentUserOrNull()
        return currentUser != null
    }
}

