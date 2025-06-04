package com.example.myapplicationtlg.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationtlg.api.repository.ExerciseRepository
import com.example.myapplicationtlg.api.repository.WorkoutRepository
import com.example.myapplicationtlg.databinding.ActivityWorkoutFormBinding
import com.example.myapplicationtlg.entity.Exercise
import com.example.myapplicationtlg.ui.adapter.ExerciseAdapter
import kotlinx.coroutines.launch


class WorkoutFormActivity : BaseActivity() {
    private lateinit var binding: ActivityWorkoutFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbarTitle("Nuevo entrenamiento")

        binding.rvExercises.layoutManager = LinearLayoutManager(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val exRep = ExerciseRepository()

        val workoutRep = WorkoutRepository()

        binding.rvExercises.layoutManager = LinearLayoutManager(this)
        var aux: ArrayList<Exercise> = ArrayList()
        lifecycleScope.launch {
            val exercises = exRep.getExercises()

            val wkAdapter = ExerciseAdapter(exercises) { exercise, isChecked ->
                if (isChecked) {
                    aux.add(exercise)
                } else {
                    if (aux.contains(exercise)) aux.remove(exercise)
                }
            }

            binding.rvExercises.adapter = wkAdapter
        }

        binding.btnSave1.setOnClickListener {
            if (validateWorkout(binding.etWorkoutName.text.toString(), binding.etWorkoutDesc.text.toString(), aux)){
                lifecycleScope.launch {
                    workoutRep.saveWorkout(binding.etWorkoutName.text.toString(), binding.etWorkoutDesc.text.toString(), aux)
                    setResult(RESULT_OK)
                    finish()
                }

            } else {
                showShortToast("Debes completar todos los campos")
            }

        }
    }

    fun validateWorkout(name:String, description: String, exercises: List<Exercise>): Boolean{
        return !((name.trim() == "") or (description.trim() == "") or (exercises.isEmpty()))
    }


}
