package com.example.myapplicationtlg.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationtlg.api.repository.WorkoutRepository
import com.example.myapplicationtlg.databinding.ActivityDashboardBinding
import com.example.myapplicationtlg.ui.adapter.WorkoutAdapter
import kotlinx.coroutines.launch


class DashboardActivity : BaseActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var rep: WorkoutRepository
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbarTitle("Entrenamientos")

        rep = WorkoutRepository()

        binding.rvWorkouts.layoutManager = LinearLayoutManager(this)

        updateData()

        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                updateData()
            }
        }

        binding.fabAddWorkout.setOnClickListener {
            launcher.launch(Intent(this, WorkoutFormActivity::class.java))
        }

        binding.fabAddRoutine.setOnClickListener {
            launcher.launch(Intent(this, RoutineSelectionActivity::class.java))
        }
    }

    fun updateData(){
        lifecycleScope.launch {
            val workouts = rep.getWorkouts()
            val adapter = WorkoutAdapter(workouts){
                val intent =
                    Intent(this@DashboardActivity, WorkoutDetailActivity::class.java)

                intent.putExtra("workout", it)
                launcher.launch(intent)
            }

            binding.rvWorkouts.adapter = adapter
        }
    }
}