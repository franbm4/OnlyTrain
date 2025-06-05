package com.example.myapplicationtlg.api.repository

import android.util.Log
import com.example.myapplicationtlg.api.Client
import com.example.myapplicationtlg.entity.Exercise
import com.example.myapplicationtlg.entity.Routine
import com.example.myapplicationtlg.entity.RoutineWithExercises
import com.example.myapplicationtlg.entity.Workout
import com.example.myapplicationtlg.entity.WorkoutWithExercises
import com.example.myapplicationtlg.entity.relation.RoutineExercise
import com.example.myapplicationtlg.entity.relation.WorkoutExercise
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class RoutineRepository {
    private lateinit var supabaseClient: SupabaseClient

    constructor(){
        supabaseClient = Client.getInstance()
    }

    suspend fun getRoutines(): List<RoutineWithExercises> {
        val routines : List<Routine> = supabaseClient.from("routine").select(Columns.ALL).decodeList<Routine>()

        val aux : ArrayList<RoutineWithExercises> = ArrayList()

        for (routine in routines){
            val routine_exercise_ids: List<RoutineExercise> = supabaseClient.from("exercise_routine").select {
                filter {
                    RoutineExercise::id_routine eq routine.id
                }
            }.decodeList()
            Log.d("WORKOUT", routine_exercise_ids.size.toString())

            val exercises: List<Exercise> = supabaseClient.from("exercise").select(Columns.ALL){
                filter {
                    Exercise::id isIn  (routine_exercise_ids.map { it.id_exercise })
                }
            }.decodeList()

            aux.add(RoutineWithExercises(routine.id, routine.name, routine.description, exercises))
        }

        return aux

    }
}