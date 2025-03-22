package com.jnu.database.dao

import androidx.room.*
import com.jnu.model.model.ReportEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReportDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReports(reportEntities: List<com.jnu.model.model.ReportEntity>)

    @Query("SELECT * FROM report WHERE id = :reportId")
    suspend fun getReportById(reportId: Long): com.jnu.model.model.ReportEntity?

    @Update
    suspend fun updateReport(reportEntity: com.jnu.model.model.ReportEntity)

    // 내 반려동물 목격 제보 가져오기
    @Query("SELECT * FROM report WHERE isOwnReport = 1")
    fun getOwnReports(): Flow<List<com.jnu.model.model.ReportEntity>>

    // 근처 목격 제보 가져오기
    @Query("SELECT * FROM report WHERE isOwnReport = 0")
    fun getNearbyReports(): Flow<List<com.jnu.model.model.ReportEntity>>

    @Query("DELETE FROM report")
    fun deleteAllFromReportTable()
}