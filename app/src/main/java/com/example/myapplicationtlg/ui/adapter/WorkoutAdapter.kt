package com.example.myapplicationtlg.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtlg.R
import com.example.myapplicationtlg.entity.Workout
import com.example.myapplicationtlg.entity.WorkoutWithExercises

class WorkoutAdapter(val workoutList: List<WorkoutWithExercises>, val onItemClick: (workout: WorkoutWithExercises) -> Unit): RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvWorkoutName: TextView = itemView.findViewById(R.id.tvWorkoutName)
        val tvWorkoutDesc: TextView = itemView.findViewById(R.id.tvWorkoutDesc)
        val root: View = itemView
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_workout, parent, false)
        return WorkoutViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: WorkoutViewHolder,
        position: Int
    ) {
        val workout = workoutList[position]

        holder.tvWorkoutName.text = workout.name
        holder.tvWorkoutDesc.text = workout.description
        holder.root.setOnClickListener {
            onItemClick(workout)
        }
    }

    override fun getItemCount(): Int {
        return workoutList.size
    }
}