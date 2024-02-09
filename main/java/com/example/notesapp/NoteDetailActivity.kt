package com.example.notesapp


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import com.example.notesapp.databinding.ActivityNoteDetailBinding

class NoteDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the note data from the intent
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")


        // Set the note data to the TextViews in the layout
        binding.noteDetailTitle.text = title
        binding.noteDetailContent.text = content

        // Set up the click listener for the back button
        binding.backButton.setOnClickListener {
            NavUtils.navigateUpFromSameTask(this)
            finish()
        }

    }


}
