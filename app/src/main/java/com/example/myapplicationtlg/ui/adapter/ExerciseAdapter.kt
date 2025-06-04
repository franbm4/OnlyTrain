package com.example.myapplicationtlg.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtlg.R
import com.example.myapplicationtlg.entity.Exercise

class ExerciseAdapter(private val exercises: List<Exercise>, private val onChangeSelectExercise: (exercise: Exercise, selected: Boolean) -> Unit): RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvExerciseName)
        val cbExercise: CheckBox = itemView.findViewById(R.id.cbSelect)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val ex: Exercise = exercises[position]
        holder.tvName.text = ex.name
        holder.cbExercise.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("HOLDER", "Hoal")
            onChangeSelectExercise(ex, isChecked)
        }
    }

    override fun getItemCount(): Int {
        return exercises.size
    }
}