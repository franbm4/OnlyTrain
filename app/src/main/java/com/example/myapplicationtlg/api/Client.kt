package com.example.myapplicationtlg.api

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

class Client {
    companion object {
        val supabaseUrl = "https://fjelqvidnbfbmapyyjgp.supabase.co"
        val supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZqZWxxdmlkbmJmYm1hcHl5amdwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDg3OTUxMjQsImV4cCI6MjA2NDM3MTEyNH0.EY3Pt_b_CeHSCZrQ6fgMwPqga_LwMs3l7QMY7lPUdLU" // Reemplaza con tu clave API (anon o service_role)
        @Volatile private var INSTANCE: SupabaseClient? = null
        fun getInstance(): SupabaseClient =
            INSTANCE ?: synchronized(this){
                createSupabaseClient(
                    supabaseUrl = supabaseUrl,
                    supabaseKey = supabaseKey
                ) {
                    install(Postgrest)
                    install(Auth){
                        autoLoadFromStorage = true
                        autoSaveToStorage = true
                    }
                }.also { INSTANCE = it }
            }


    }
}