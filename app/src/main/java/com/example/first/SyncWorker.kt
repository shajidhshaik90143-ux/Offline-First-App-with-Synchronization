package com.example.first.network

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.first.data.AppDatabase

class SyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val db = AppDatabase.getDatabase(applicationContext)
        val unsynced = db.noteDao().getUnsyncedNotes()

        // Simulated server sync
        unsynced.forEach {
            println("Syncing note: ${it.text}")
        }

        db.noteDao().markAllSynced()
        return Result.success()
    }
}
