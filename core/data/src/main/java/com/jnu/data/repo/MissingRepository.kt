package com.jnu.data.repo

import android.content.Context
import android.util.Log
import com.jnu.network.datasource.MissingSource
import com.jnu.network.model.MissingRegisterRequestDTO
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MissingRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val missingSource: com.jnu.network.datasource.MissingSource,
    private val tokenRepository: TokenRepository,
    private val missingDao: com.jnu.database.dao.MissingDao
) {
    suspend fun registerMissing(
        missingRegisterRequestDTO: com.jnu.network.model.MissingRegisterRequestDTO
    ) {
        missingSource.registerMissing(
            tokenRepository.getTokenOrThrow(),
            missingRegisterRequestDTO
        )
    }

    suspend fun getMissingNearBy(
        latitude: Double,
        longitude: Double
    ) {
        Log.d("yeong", "MissingRepository")
        missingDao.deleteAllFromMissingTable()
        missingDao.insertMissing(
            missingSource.getMissingNearBy(latitude, longitude)
                .map { missing ->
                    com.jnu.database.model.MissingEntity(
                        missing.missingId,
                        missing.petId,
                        missing.latitude,
                        missing.longitude,
                        mutableListOf(missing.petImageUrl),
                        null,
                        null,
                        null,
                        null,
                    )
                }
        )
    }

    suspend fun getMissingByMissingId(
        missingId: Long
    ): com.jnu.database.model.MissingEntity? {
        val findMissing = missingDao.getMissing(missingId)
        Log.d("MissingRepository", "Initial findMissing: $findMissing")

        if (findMissing != null) {
            val detailMissing = missingSource.getMissingByMissingId(missingId)
            findMissing.petImageUrl.addAll(detailMissing.imageUrl)
            val updateMissing = findMissing.copy(
                name = detailMissing.name,
                breed = detailMissing.breed,
                birthMonth = detailMissing.birthMonth,
                description = detailMissing.description,
            )
            missingDao.updateMissing(updateMissing)
            Log.d("MissingRepository", "Updated missing entity: $updateMissing")
            return updateMissing
        }
        return null
    }

    fun getAllMissingReports(): Flow<List<com.jnu.database.model.MissingEntity>> = missingDao.getAllMissingReports()
}