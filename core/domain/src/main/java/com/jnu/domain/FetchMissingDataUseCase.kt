package com.jnu.domain

import com.jnu.data.repo.MissingRepository
import com.jnu.data.repo.ReportRepository
import com.jnu.model.entities.MissingEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchMissingDataUseCase @Inject constructor(private val missingRepository: MissingRepository) {
    suspend operator fun invoke(latitude: Double,longitude: Double) = missingRepository.getMissingNearBy(latitude, longitude)
}