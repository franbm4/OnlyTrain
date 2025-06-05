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

class RoutineExerciseAdapter(private val exercises: List<Exercise>): RecyclerView.Adapter<RoutineExerciseAdapter.RoutineExerciseViewHolder>() {

    class RoutineExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvExerciseName: TextView = itemView.findViewById(R.id.tvrDetailExerciseName)
        val tvExerciseDesc: TextView = itemView.findViewById(R.id.tvrDetailExerciseDescription)

        fun setOnClickListener(listener: ()-> Unit){
            itemView.setOnClickListener {
                listener()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RoutineExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise_routine_detail, parent, false)
        return RoutineExerciseViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RoutineExerciseViewHolder,
        position: Int
    ) {
        val exercise = exercises[position]

        holder.tvExerciseName.text = exercise.name
        holder.tvExerciseDesc.text = exercise.description

        holder.setOnClickListener {
            holder.tvExerciseDesc.visibility = if(holder.tvExerciseDesc.isVisible) View.GONE else View.VISIBLE
        }
    }

    override fun getItemCount(): Int = exercises.size


}