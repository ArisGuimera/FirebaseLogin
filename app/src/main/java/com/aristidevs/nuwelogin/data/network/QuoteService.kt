package com.aristidevs.nuwelogin.data.network

import com.aristidevs.nuwelogin.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteService @Inject constructor(var quoteApiClient: QuoteApiClient){

    suspend fun getQuotes(): List<QuoteModel> {
        return withContext(Dispatchers.IO) {
            val response = quoteApiClient.getAllQuotes()
            response.body() ?: emptyList()
        }
    }

}