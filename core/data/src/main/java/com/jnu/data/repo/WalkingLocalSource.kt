package com.jnu.data.repo

import com.jnu.model.entities.WalkEntity
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalkingLocalSource @Inject constructor(
    private val walkDao: com.jnu.database.dao.WalkDao
) {
    suspend fun insertWalkingData(
        walkEntity: WalkEntity
    ) {
        walkDao.insertWalk(walkEntity)
    }

    suspend fun readWalkingDataWithDate(date: LocalDate): List<WalkEntity>? {
        return walkDao.getWalksByDay(date)
    }
}