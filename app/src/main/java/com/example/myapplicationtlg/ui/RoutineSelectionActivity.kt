package com.example.myapplicationtlg.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationtlg.api.repository.RoutineRepository
import com.example.myapplicationtlg.api.repository.WorkoutRepository

import com.example.myapplicationtlg.databinding.ActivityRoutineSelectionBinding
import com.example.myapplicationtlg.entity.RoutineWithExercises
import com.example.myapplicationtlg.ui.adapter.RoutineAdapter
import kotlinx.coroutines.launch


class RoutineSelectionActivity : BaseActivity() {
    private lateinit var binding: ActivityRoutineSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutineSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setToolbarTitle("Nuestras rutinas")

        val routineRep = RoutineRepository()
        val workoutRep = WorkoutRepository()

        val routinesAux = ArrayList<RoutineWithExercises>()

        binding.routineRecyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {

            val adapter = RoutineAdapter(routineRep.getRoutines(), { routine, isChecked ->
                if (isChecked) routinesAux.add(routine)
                if (routinesAux.size==1){
                   binding.btnSave2.text = "Añadir Rutina"
                }else{
                    binding.btnSave2.text = "Añadir Rutinas"
                }
            }, {
                val intent = Intent(this@RoutineSelectionActivity, RoutineDetailsActivity::class.java)
                intent.putExtra("routine", it)
                startActivity(intent)
            })

            binding.routineRecyclerView.adapter = adapter
        }

        binding.btnSave2.setOnClickListener {
            lifecycleScope.launch {
                for (routine in routinesAux) {
                    workoutRep.saveRoutineAsWorkout(routine)
                    setResult(RESULT_OK)
                    finish()
                }
            }
        }

    }
}