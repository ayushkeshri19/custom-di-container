package com.ayush.dicontainer.data.impl

import android.util.Log
import com.ayush.dicontainer.data.ApiClient
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class ApiClientImpl: ApiClient {
    companion object {
        private const val TAG: String = "ApiClientImpl"
    }

    override suspend fun get() {
        Log.d(TAG, "Fetching data")
        delay(1100.milliseconds)
        Log.d(TAG, "Data fetched successfully!")
    }
}