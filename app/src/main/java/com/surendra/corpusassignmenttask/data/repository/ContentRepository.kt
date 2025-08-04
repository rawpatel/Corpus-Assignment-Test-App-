package com.surendra.corpusassignmenttask.data.repository

import android.content.Context
import com.google.gson.Gson
import com.surendra.corpusassignmenttask.data.model.ApiResponse
import com.surendra.corpusassignmenttask.data.model.ContentSection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class ContentRepository(private val context: Context) {
    
    private val gson = Gson()
    
    suspend fun getContentSections(): List<ContentSection> = withContext(Dispatchers.IO) {
        try {
            val jsonString = loadJSONFromAsset("carousel.json")
            val apiResponse = gson.fromJson(jsonString, ApiResponse::class.java)
            
            if (apiResponse.responseStatus.statusCode == "200") {
                return@withContext apiResponse.content.sortedBy { it.displayOrder }
            } else {
                throw Exception("API Error: ${apiResponse.responseStatus.statusMessage}")
            }
        } catch (e: Exception) {
            throw Exception("Failed to load content: ${e.message}")
        }
    }
    
    private fun loadJSONFromAsset(fileName: String): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }
}