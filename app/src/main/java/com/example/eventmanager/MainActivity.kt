package com.example.eventmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), IEventAdapter {

private lateinit var binding: ActivityMainBinding
lateinit var viewModel: EventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.fabCreate.setOnClickListener {
            val intent = Intent(this, CreateEventActivity::class.java)
            startActivity(intent)
        }

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = EventAdapter(this, this)
        binding.recyclerview.adapter = adapter

        viewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(EventViewModel::class.java)
        viewModel.allEvents.observe(this, Observer {list ->
            list?.let{
                adapter.updateList(it)
            }
        })
    }
    override fun onItemClick(event: Event) {
        viewModel.delete(event)
    }

}