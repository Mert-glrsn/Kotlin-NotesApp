package com.example.notesapp
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        try {
            // Fetch data from API
            val mockApiService = MockApiService()
            val notesManager = NotesManager(applicationContext)

            val newData = mockApiService.fetchData()
            notesManager.updateNotes(newData)

            return Result.success()
        } catch (e: Exception) {
            // Handle errors
            return Result.failure()
        }
    }
}
