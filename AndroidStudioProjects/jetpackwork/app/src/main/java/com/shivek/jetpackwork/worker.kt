package com.shivek.jetpackwork

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class worker(val context : Context , val parmam : WorkerParameters) : CoroutineWorker(context,parmam)
{
    override suspend fun doWork(): Result {

        val response = withContext(Dispatchers.IO){client.response.execute()}
        return if (response.isSuccessful)
        {
             Result.success()
        }
        else
            Result.retry()
    }

}