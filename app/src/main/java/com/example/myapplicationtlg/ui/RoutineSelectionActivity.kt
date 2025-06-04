package com.example.myapplicationtlg.ui

import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager

import com.example.myapplicationtlg.databinding.ActivityRoutineSelectionBinding


class RoutineSelectionActivity : BaseActivity() {
    private lateinit var binding: ActivityRoutineSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutineSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbarTitle("Nuestras rutinas")

        binding.routineRecyclerView.layoutManager = LinearLayoutManager(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }
}