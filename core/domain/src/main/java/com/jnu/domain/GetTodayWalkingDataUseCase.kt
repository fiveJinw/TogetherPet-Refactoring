package com.jnu.domain

import com.jnu.data.repo.WalkingRepository
import com.jnu.model.WalkingData
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class GetTodayWalkingDataUseCase @Inject constructor(private val walkingRepository: WalkingRepository) {
    suspend operator fun invoke() : WalkingData {
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        var allDistance : Long = 0L
        var allTime : Long = 0L
        var walkCount : Int = 0

        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        val walkingRecordList = walkingRepository.getWalkingDataWithDateFromLocal(LocalDate.now())
        walkingRecordList.forEach {
            allDistance += it.distance
            allTime += it.time
            walkCount += 1
        }
        return WalkingData(
            distance = allDistance,
            time = dateFormat.format(Date(allTime)),
            todayWalkCount = walkCount
        )
    }
}
