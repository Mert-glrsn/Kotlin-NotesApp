package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db:NoteDBHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db= NoteDBHelper(this)
        notesAdapter=NotesAdapter(db.getAllNotes(), this)
        binding.notesRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.notesRecyclerView.adapter=notesAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this, com.example.notesapp.AddNotes::class.java)
            startActivity(intent)
        }

        binding.seeWeather.setOnClickListener {
            // Create an Intent to start the Weather activity
            val intent = Intent(this, Weather::class.java)

            // Start the Weather activity
            startActivity(intent)
        }

        val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setInitialDelay(3, TimeUnit.SECONDS) // Simulate network delay
            .build()

        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }
}