package com.example.myapplicationtlg.api.repository

import com.example.myapplicationtlg.api.Client
import com.example.myapplicationtlg.entity.Exercise
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class ExerciseRepository {
    private lateinit var supabaseClient: SupabaseClient

    constructor(){
        supabaseClient = Client.getInstance()
    }

    suspend fun getExercises(): List<Exercise>{
        return supabaseClient.from("exercise").select(Columns.ALL).decodeList<Exercise>()
    }
}