package com.jnu.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jnu.database.dao.MissingDao
import com.jnu.database.model.MissingEntity
import com.jnu.database.util.TypeConverterModule

@Database(entities = [com.jnu.database.model.MissingEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverterModule::class)
abstract class MissingDataBase : RoomDatabase() {
    abstract fun missingDao(): com.jnu.database.dao.MissingDao
}