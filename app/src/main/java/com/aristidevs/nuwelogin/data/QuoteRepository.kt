package com.aristidevs.nuwelogin.data

import com.aristidevs.nuwelogin.data.model.QuoteModel
import com.aristidevs.nuwelogin.data.model.QuoteProvider
import com.aristidevs.nuwelogin.data.network.QuoteService
import javax.inject.Inject

class QuoteRepository @Inject constructor(var api: QuoteService, var quoteProvider: QuoteProvider) {

    suspend fun getAllQuotes(): List<QuoteModel> {
        val response = api.getQuotes()
        quoteProvider.quotes = response
        return response
    }
}