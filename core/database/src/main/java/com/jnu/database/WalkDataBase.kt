package com.jnu.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jnu.database.util.TypeConverterModule
import com.jnu.model.entities.WalkEntity

@Database(entities = [WalkEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverterModule::class)
abstract class WalkDataBase : RoomDatabase() {
    abstract fun walkDao(): com.jnu.database.dao.WalkDao
}