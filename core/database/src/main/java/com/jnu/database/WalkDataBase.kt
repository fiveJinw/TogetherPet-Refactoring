package com.jnu.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jnu.database.dao.WalkDao
import com.jnu.model.model.WalkEntity
import com.jnu.database.util.TypeConverterModule

@Database(entities = [com.jnu.model.model.WalkEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverterModule::class)
abstract class WalkDataBase : RoomDatabase() {
    abstract fun walkDao(): com.jnu.database.dao.WalkDao
}