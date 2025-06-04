package com.example.myapplicationtlg.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationtlg.databinding.ActivityWorkoutDetailBinding
import com.example.myapplicationtlg.entity.WorkoutWithExercises

class WorkoutDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityWorkoutDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION") val workout = intent.getParcelableExtra("workout") as? WorkoutWithExercises

        workout?.let {

            binding.dtWorkoutName.text = it.name
            binding.dtWorkoutDesc.text = it.description

        } ?: run {
            Log.e("Workout", "Objeto no encontrado")
        }
    }
}