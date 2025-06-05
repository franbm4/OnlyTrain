package com.example.myapplicationtlg.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtlg.R
import com.example.myapplicationtlg.entity.WorkoutWithExercises
import androidx.core.view.isVisible
import com.example.myapplicationtlg.entity.Exercise

class WorkoutExerciseAdapter(private val exercises: List<Exercise>): RecyclerView.Adapter<WorkoutExerciseAdapter.WorkoutExerciseViewHolder>() {

    class WorkoutExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvExerciseName: TextView = itemView.findViewById(R.id.tvDetailExerciseName)
        val tvExerciseDesc: TextView = itemView.findViewById(R.id.tvDetailExerciseDescription)

        fun setOnClickListener(listener: ()-> Unit){
            itemView.setOnClickListener {
                listener()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WorkoutExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise_workout_detail, parent, false)
        return WorkoutExerciseViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: WorkoutExerciseViewHolder,
        position: Int
    ) {
        val exercise = exercises[position]

        holder.tvExerciseName.text = exercise.name
        holder.tvExerciseDesc.text = exercise.description

        holder.setOnClickListener {
            holder.tvExerciseDesc.visibility = if(holder.tvExerciseDesc.isVisible) View.GONE else View.VISIBLE
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = exercises.size


}