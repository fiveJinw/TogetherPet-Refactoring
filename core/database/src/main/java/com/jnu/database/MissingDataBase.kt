package com.jnu.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jnu.database.dao.MissingDao
import com.jnu.database.util.TypeConverterModule
import com.jnu.model.entities.MissingEntity

@Database(entities = [MissingEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverterModule::class)
abstract class MissingDataBase : RoomDatabase() {
    abstract fun missingDao(): MissingDao
}