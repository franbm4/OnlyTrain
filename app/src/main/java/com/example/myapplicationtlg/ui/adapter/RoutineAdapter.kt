package com.example.myapplicationtlg.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtlg.R
import com.example.myapplicationtlg.entity.RoutineWithExercises

class RoutineAdapter(private val routines: List<RoutineWithExercises>, private val onRoutineCheck: (routine: RoutineWithExercises, selected: Boolean)-> Unit, private val onRoutineClick: (routine: RoutineWithExercises) -> Unit): RecyclerView.Adapter<RoutineAdapter.RoutineViewHolder>() {

    class RoutineViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvRoutineTitle: TextView = itemView.findViewById(R.id.routineTitle)
        val tvRoutineDescription: TextView = itemView.findViewById(R.id.routineDescription)
        val cbRoutine: CheckBox = itemView.findViewById(R.id.cbRoutine)
        val root: View = itemView
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RoutineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_routine, parent, false)

        return RoutineViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RoutineViewHolder,
        position: Int
    ) {
        val routine = routines[position]

        holder.tvRoutineTitle.text = routine.name
        holder.tvRoutineDescription.text = routine.description

        holder.cbRoutine.setOnCheckedChangeListener { buttonView, isChecked ->
            onRoutineCheck(routine, isChecked)
        }

        holder.root.setOnClickListener {
            onRoutineClick(routine)
        }
    }

    override fun getItemCount(): Int = routines.size


}