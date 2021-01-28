package com.crisspian.shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.crisspian.shared.databinding.FragmentSecondBinding
import com.crisspian.shared.model.Task

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    fun saveData() {
        val titulo = binding.etTitulo.text.toString()
        val descripcion = binding.etmDescripcion.text.toString()
        val fecha = binding.etFecha.text.toString()
        val prioridad = binding.etPrioridad.text.toString().toInt()
        val estado = binding.cbEstado.isChecked

        val newTask = Task(title = titulo, taskDescription = descripcion, date = fecha,
                priority = prioridad, state = estado)
    }
}