package com.example.myapplicationtlg.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationtlg.api.repository.WorkoutRepository
import com.example.myapplicationtlg.databinding.ActivityWorkoutDetailBinding
import com.example.myapplicationtlg.entity.WorkoutWithExercises
import com.example.myapplicationtlg.ui.adapter.WorkoutExerciseAdapter
import kotlinx.coroutines.launch

class WorkoutDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityWorkoutDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbarTitle("Detalles del entrenamiento")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val workoutRep = WorkoutRepository()

        @Suppress("DEPRECATION") val workout = intent.getParcelableExtra("workout") as? WorkoutWithExercises

        workout?.let {
            binding.dtWorkoutName.text = it.name
            binding.dtWorkoutDesc.text = it.description

            val adapter = WorkoutExerciseAdapter(it.exercises)

            binding.rvExercisesWorkout.layoutManager = LinearLayoutManager(this)
            binding.rvExercisesWorkout.adapter = adapter

            binding.btnDelete.setOnClickListener {
                lifecycleScope.launch {
                    if (workout.id != null)  workoutRep.deleteWorkoutById(workout.id)
                    setResult(RESULT_OK)
                    finish()
                }
            }

        } ?: run {
            Log.e("Workout", "Objeto no encontrado")
        }
    }
}