package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesapp.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: NoteDBHelper
    private var noteId : Int =-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = NoteDBHelper(this)
        noteId = intent.getIntExtra("note_id" , -1)
        if(noteId==-1){
           finish()
           return
        }
        val note=db.getNoteByID(noteId)
        binding.updatetTitleEdit.setText(note.title)
        binding.updateContentEdit.setText(note.content)
        binding.updateSaveButton.setOnClickListener{
            val newTitle = binding.updatetTitleEdit.text.toString()
            val newContent = binding.updateContentEdit.text.toString()
            val updateNote = Note(noteId, newTitle , newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this , "Changes Saved" , Toast.LENGTH_SHORT).show()
        }
    }
}