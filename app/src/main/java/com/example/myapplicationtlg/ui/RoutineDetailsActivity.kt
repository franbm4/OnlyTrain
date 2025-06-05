package com.example.myapplicationtlg.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationtlg.R
import com.example.myapplicationtlg.databinding.ActivityRoutineDetailsBinding
import com.example.myapplicationtlg.databinding.ActivityWorkoutDetailBinding
import com.example.myapplicationtlg.entity.RoutineWithExercises
import com.example.myapplicationtlg.entity.WorkoutWithExercises
import com.example.myapplicationtlg.ui.adapter.RoutineExerciseAdapter
import com.example.myapplicationtlg.ui.adapter.WorkoutExerciseAdapter
import kotlinx.coroutines.launch

class RoutineDetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityRoutineDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutineDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbarTitle("Detalles de la rutina")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        @Suppress("DEPRECATION") val routine = intent.getParcelableExtra("routine") as? RoutineWithExercises

        routine?.let {
            binding.dtRoutineName.text = it.name
            binding.dtRoutineDesc.text = it.description

            val adapter = RoutineExerciseAdapter(it.exercises)

            binding.rvExercisesRoutine.layoutManager = LinearLayoutManager(this)
            binding.rvExercisesRoutine.adapter = adapter

        } ?: run {
            Log.e("Workout", "Objeto no encontrado")
        }
    }
}