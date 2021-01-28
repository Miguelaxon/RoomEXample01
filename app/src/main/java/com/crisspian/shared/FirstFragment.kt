package com.crisspian.shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.crisspian.shared.databinding.FragmentFirstBinding
import com.crisspian.shared.model.Task
import com.crisspian.shared.model.TaskAdapter
import com.crisspian.shared.model.TaskViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = TaskAdapter()
        binding.rvTask.adapter = adapter
        binding.rvTask.layoutManager = LinearLayoutManager(context)

        val task = Task(1,"Titulo 1","Descripcion 1","27/01/2021"
            ,2,false)
        val task2 = Task(2,"Titulo 2","Descripcion 2","28/01/2021"
            ,1,false)
        val task3 = Task(3,"Titulo 3","Descripcion 3","26/01/2021"
            ,1,true)

        viewModel.insertTask(task)
        viewModel.insertTask(task2)
        viewModel.insertTask(task3)

        viewModel.allTask.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.update(it)
            /*println(it)
            Log.d("lista", it.toString())*/
        })
    }
}