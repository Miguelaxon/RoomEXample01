package com.crisspian.shared.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(appication: Application): AndroidViewModel(appication) {
    private val repository: TaskRepository
    val allTask: LiveData<List<Task>>

    init {
        val taskDao = TaskDataBase.getDataBase(appication).getTaskDao()
        repository = TaskRepository(taskDao)
        allTask = repository.listAllTask
    }

    fun insertTask(task: Task) = viewModelScope.launch {
        repository.createTask(task)
    }

    fun deleteAllTask() = viewModelScope.launch {
        repository.deleteAllTask()
    }
}