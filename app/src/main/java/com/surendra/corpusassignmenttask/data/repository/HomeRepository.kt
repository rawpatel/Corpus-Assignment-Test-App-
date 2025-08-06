package com.surendra.corpusassignmenttask.data.repository

import android.content.Context
import com.google.gson.Gson
import com.surendra.corpusassignmenttask.data.model.HomeContent
import com.surendra.corpusassignmenttask.data.model.HomeResponse
import com.surendra.corpusassignmenttask.utils.Constants.STATUS_CODE_SUCCESS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class HomeRepository {
    
    private val gson = Gson()
    
    suspend fun getHomeContent(context: Context): List<HomeContent> = withContext(Dispatchers.IO) {
        try {
            val jsonString = loadJsonFromAssets(context, "carousal.json")
            val homeResponse = gson.fromJson(jsonString, HomeResponse::class.java)
            
            if (homeResponse.responseStatus.statusCode == STATUS_CODE_SUCCESS) {
                homeResponse.content
            } else {
                throw Exception("API Error: ${homeResponse.responseStatus.statusMessage}")
            }
        } catch (e: Exception) {
            throw Exception("Failed to load home content: ${e.message}")
        }
    }
    
    private fun loadJsonFromAssets(context: Context, fileName: String): String {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ex: IOException) {
            throw Exception("Could not load JSON file: $fileName")
        }
    }
}