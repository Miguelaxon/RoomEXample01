package com.crisspian.shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.crisspian.shared.databinding.FragmentSecondBinding
import com.crisspian.shared.model.Task
import com.crisspian.shared.model.TaskViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel : TaskViewModel by activityViewModels()
    private var idTask: Int = 0
    private var taskSelected : Task? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedItem().observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.etTitulo.setText(it.title)
                binding.etmDescripcion.setText(it.taskDescription)
                binding.etFecha.setText(it.date)
                binding.etPrioridad.setText(it.priority.toString())
                binding.cbEstado.isChecked = it.state
                idTask = it.id
                taskSelected = it
            }
        })

        binding.btnGuardar.setOnClickListener {
            saveData()
            viewModel.selected(null)
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }

    fun saveData() {
        val titulo = binding.etTitulo.text.toString()
        val descripcion = binding.etmDescripcion.text.toString()
        val fecha = binding.etFecha.text.toString()
        val prioridad = binding.etPrioridad.text.toString().toInt()
        val estado = binding.cbEstado.isChecked

        if (titulo.isNullOrEmpty()){// || descripcion.isBlank() || fecha.isBlank()) {
            Toast.makeText(activity, "Debes añadir los datos", Toast.LENGTH_LONG).show()
        } else if (idTask == 0) {
            val newTask = Task(title = titulo, taskDescription = descripcion, date = fecha,
                    priority = prioridad, state = estado)
            viewModel.insertTask(newTask)
            } else {
            val newTask = Task(id = idTask, title = titulo, taskDescription = descripcion, date = fecha,
                    priority = prioridad, state = estado)
            viewModel.updateTask(newTask)
            }
    }
}