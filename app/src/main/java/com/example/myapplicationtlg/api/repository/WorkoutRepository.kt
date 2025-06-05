package com.example.myapplicationtlg.api.repository

import android.util.Log
import com.example.myapplicationtlg.api.Client
import com.example.myapplicationtlg.entity.Exercise
import com.example.myapplicationtlg.entity.RoutineWithExercises
import com.example.myapplicationtlg.entity.Workout
import com.example.myapplicationtlg.entity.WorkoutWithExercises
import com.example.myapplicationtlg.entity.relation.WorkoutExercise
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class WorkoutRepository {
    private lateinit var supabaseClient: SupabaseClient

    constructor(){
        supabaseClient = Client.getInstance()
    }

    suspend fun saveWorkout(name: String, description: String, exercises: List<Exercise>){
        val workoutData = mapOf(
            "name" to name,
            "description" to description
        )
        val workoutInsert = supabaseClient.from("workout").insert(workoutData){
            select(Columns.ALL)
        }.decodeSingle<Workout>()

        for (exercise in exercises){
            val workoutExerciseData = mapOf(
                "id_exercise" to exercise.id,
                "id_workout" to workoutInsert.id
            )

            supabaseClient.from("exercise_workout").insert(workoutExerciseData)
        }

    }

    suspend fun saveRoutineAsWorkout(routine: RoutineWithExercises){
        val workoutData = mapOf(
            "name" to routine.name,
            "description" to routine.description
        )
        val workoutInsert = supabaseClient.from("workout").insert(workoutData){
            select(Columns.ALL)
        }.decodeSingle<Workout>()

        for (exercise in routine.exercises){
            val workoutExerciseData = mapOf(
                "id_exercise" to exercise.id,
                "id_workout" to workoutInsert.id
            )

            supabaseClient.from("exercise_workout").insert(workoutExerciseData)
        }

    }

    suspend fun deleteWorkoutById(id: Int){
        supabaseClient.from("workout").delete{
            filter {
                Workout::id eq id
            }
        }
    }

    suspend fun getWorkouts(): List<WorkoutWithExercises> {
        val workouts : List<Workout> = supabaseClient.from("workout").select(Columns.ALL).decodeList<Workout>()

        val aux : ArrayList<WorkoutWithExercises> = ArrayList()

        for (workout in workouts){
            val workout_exercise_ids: List<WorkoutExercise> = supabaseClient.from("exercise_workout").select {
                filter {
                    WorkoutExercise::id_workout eq workout.id
                }
            }.decodeList()
            Log.d("WORKOUT", workout_exercise_ids.size.toString())

            val exercises: List<Exercise> = supabaseClient.from("exercise").select(Columns.ALL){
                filter {
                    Exercise::id isIn  (workout_exercise_ids.map { it.id_exercise })
                }
            }.decodeList()

            aux.add(WorkoutWithExercises(workout.id, workout.name, workout.description, exercises))
        }

        return aux

    }
}