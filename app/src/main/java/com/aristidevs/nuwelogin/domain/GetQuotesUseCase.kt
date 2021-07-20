package com.aristidevs.nuwelogin.domain

import com.aristidevs.nuwelogin.data.QuoteRepository
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(var repository: QuoteRepository) {

    suspend operator fun invoke() = repository.getAllQuotes()

}