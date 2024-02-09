package com.example.notesapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NotesManager(private val context: Context) {
    fun updateNotes(newNotes: String?) {
        // Update notes in the local database
        val dbHelper = NoteDBHelper(context)

        if (newNotes != null) {
            // Convert JSON string to List<Note>
            val notesList: List<Note> = Gson().fromJson(newNotes, object : TypeToken<List<Note>>() {}.type)
            // Insert each note into the database
            for (newNote in notesList) {
                dbHelper.insertNote(newNote)
            }
        }

        // Notify the user with a notification
        showNotification("New notes available!")
    }

    private fun showNotification(message: String) {
        println("Notification: $message")
    }


}

