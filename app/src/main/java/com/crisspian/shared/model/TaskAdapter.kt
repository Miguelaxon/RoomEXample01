package com.crisspian.shared.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.crisspian.shared.databinding.TaskItemBinding

class TaskAdapter: RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var mListTask = listOf<Task>()
    private val selectedTask = MutableLiveData<Task>()

    fun selectedItem(): LiveData<Task> = selectedTask

    fun update(listTask: List<Task>){
        mListTask = listTask
        notifyDataSetChanged()

    }

    inner class TaskViewHolder(private val binding: TaskItemBinding): RecyclerView.ViewHolder(binding.root),
    View.OnClickListener{
        fun bind(task: Task) {
            binding.cbEstadoTarea.isChecked = task.state
            binding.cbEstadoTarea.isEnabled = false
            binding.tvTitulo.text = task.title
            binding.tvDescripcion.text = task.taskDescription
            binding.tvFecha.text = task.date
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedTask.value = mListTask[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = mListTask.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = mListTask[position]
        holder.bind(task)
    }
}