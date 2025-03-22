package com.jnu.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jnu.model.entities.WalkEntity
import java.time.LocalDate

@Dao
interface WalkDao {

    @Insert
    suspend fun insertWalk(walk: WalkEntity)

    @Query("SELECT * FROM walking WHERE walkDay = :day")
    suspend fun getWalksByDay(day: LocalDate): List<WalkEntity>
}