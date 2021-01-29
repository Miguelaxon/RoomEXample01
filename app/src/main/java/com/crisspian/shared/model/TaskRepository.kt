package com.crisspian.shared.model

import androidx.lifecycle.LiveData

class TaskRepository (private val taskDao: TaskDao) {
    //Contiene todos los datos desde la BBDD
    val listAllTask: LiveData<List<Task>> = taskDao.getAllTask()
    //Crea una tarea en la BBDD
    suspend fun createTask(task: Task) {
        taskDao.createTask(task)
    }
    //Elimina la tarea creada en la BBDD
    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
    //Elimina todos los datos
    suspend fun deleteAllTask() {
        taskDao.deleteAllTask()
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }
}