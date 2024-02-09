package com.example.notesapp

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MockApiService {
    fun fetchData(): String? {
        Thread.sleep(3000*3000)

        val note = Note(1, "MockApiNote 1", "This is created by mock api")
        val listOfNotes = listOf(note)
        val jsonString = Gson().toJson(listOfNotes)
        return Gson().toJson(listOfNotes)

    }
}