package com.example.eventmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.eventmanager.databinding.ActivityCreateEventBinding

class CreateEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateEventBinding
    lateinit var viewModel: EventViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(EventViewModel::class.java)


        binding.fabDone.setOnClickListener {
            val header: String = binding.etTitle.text.toString()
            val text: String = binding.etText.text.toString()
            val intent = Intent(this, MainActivity::class.java)

            if (header.isNotEmpty() && text.isNotEmpty()){
                viewModel.insert(Event(0,header,text))
            }
            Toast.makeText(this, "Event added", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }
}