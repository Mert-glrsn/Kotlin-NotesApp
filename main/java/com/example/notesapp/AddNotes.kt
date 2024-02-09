package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesapp.databinding.ActivityAddNoteBinding

class AddNotes : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db:NoteDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db= NoteDBHelper(this)

        binding.saveButton.setOnClickListener{
            val title = binding.titleEdit.text.toString()
            val content = binding.contentEdit.text.toString()
            val note = Note(0 , title , content)
            db.insertNote(note)
            Toast.makeText(this , "Note Saved" , Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}