package com.jnu.data.repo

import com.jnu.database.dao.WalkDao
import com.jnu.database.model.WalkEntity
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalkingLocalSource @Inject constructor(
    private val walkDao: com.jnu.database.dao.WalkDao
) {
    suspend fun insertWalkingData(
        walkEntity: com.jnu.database.model.WalkEntity
    ) {
        walkDao.insertWalk(walkEntity)
    }

    suspend fun readWalkingDataWithDate(date: LocalDate): List<com.jnu.database.model.WalkEntity>? {
        return walkDao.getWalksByDay(date)
    }
}