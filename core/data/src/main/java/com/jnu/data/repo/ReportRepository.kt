package com.jnu.data.repo

import android.content.Context
import android.util.Log
import com.jnu.network.datasource.ReportSource
import com.jnu.network.model.ReportCreateRequestDTO
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReportRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val reportSource: com.jnu.network.datasource.ReportSource,
    private val tokenRepository: TokenRepository,
    private val reportDao: com.jnu.database.dao.ReportDao
) {

    suspend fun registerReportByMissing(
        color: String,
        foundLatitude: Double,
        foundLongitude: Double,
        foundDate: String,  //변경(11.05)
        description: String,
        breed: String,
        gender: String,
        missingId: Long,
        files: List<File>
    ) {
        reportSource.registerReport(
            tokenRepository.getTokenOrThrow(),
            com.jnu.network.model.ReportCreateRequestDTO(
                color,
                foundLatitude,
                foundLongitude,
                foundDate,
                description,
                breed,
                gender,
                missingId
            ),
            files
        )
    }

    suspend fun registerReportWithoutMissing(
        color: String,
        foundLatitude: Double,
        foundLongitude: Double,
        foundDate: String,  //변경(11.05)
        description: String,
        breed: String,
        gender: String,
        files: List<File>
    ) {
        Log.d("yeong", "repo")

        reportSource.registerReport(
            tokenRepository.getTokenOrThrow(),
            com.jnu.network.model.ReportCreateRequestDTO(
                color,
                foundLatitude,
                foundLongitude,
                foundDate,
                description,
                breed,
                gender,
                null
            ),
            files
        )
    }

    suspend fun getReportOwnByUser() {
        reportDao.deleteAllFromReportTable()
        reportDao.insertReports(
            reportSource.getRegisterOwnByUser(tokenRepository.getTokenOrThrow())
                .map { report ->
                    com.jnu.database.model.ReportEntity(
                        report.id,
                        report.latitude,
                        report.longitude,
                        mutableListOf(report.imageUrl),
                        null,
                        null,
                        null,
                        true
                    )
                }
        )
    }

    suspend fun getReportByLocation(
        latitude: Double,
        longitude: Double,
    ) {
        Log.d("yeong", "근처 실종 데이터 받아옴")
        val reports = reportSource.getReportByLocation(latitude, longitude)
            .map { report ->
                com.jnu.database.model.ReportEntity(
                    report.id,
                    report.latitude,
                    report.longitude,
                    mutableListOf(report.imageUrl),
                    null,
                    null,
                    null,
                    false
                )
            }
        reportDao.deleteAllFromReportTable()
        reportDao.insertReports(reports)
    }

    suspend fun getReportDetail(
        reportId: Long
    ): com.jnu.database.model.ReportEntity? {
        Log.d("yoeng","ReportId 전달 : $reportId")
        val findReport = reportDao.getReportById(reportId)

        if (findReport != null) {
            val detailReport = reportSource.getReportDetail(reportId)
            Log.d("ReportRepository", "서버로 받은 reports: $detailReport")
            findReport.imageUrl.addAll(detailReport.imageUrl)
            val updateReporting = findReport.copy(
                description = detailReport.description,
                reporterName = detailReport.reporterName,
                foundDate = detailReport.foundDate.toString()
            )
            reportDao.updateReport( updateReporting)
            return updateReporting
        }
        return null
    }

    // 내 반려동물 목격 제보 가져오기
    fun getOwnReports(): Flow<List<com.jnu.database.model.ReportEntity>> = reportDao.getOwnReports()

    // 근처 목격 제보 가져오기
    fun getNearbyReports(): Flow<List<com.jnu.database.model.ReportEntity>> = reportDao.getNearbyReports()
}