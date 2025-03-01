package com.jnu.database.dao

import androidx.room.*
import com.jnu.database.model.MissingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MissingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMissing(missingEntities: List<com.jnu.database.model.MissingEntity>)

    @Query("SELECT * FROM missing WHERE id = :missingId")
    suspend fun getMissing(missingId: Long): com.jnu.database.model.MissingEntity?

    @Update(entity = com.jnu.database.model.MissingEntity::class)
    suspend fun updateMissing(missingEntity: com.jnu.database.model.MissingEntity)

    @Query("SELECT * FROM missing")
    fun getAllMissingReports(): Flow<List<com.jnu.database.model.MissingEntity>>

    @Query("DELETE FROM missing")
    fun deleteAllFromMissingTable()
}