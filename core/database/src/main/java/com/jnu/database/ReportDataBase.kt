package com.jnu.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jnu.database.dao.ReportDao
import com.jnu.database.util.TypeConverterModule
import com.jnu.model.entities.ReportEntity

@Database(entities = [ReportEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverterModule::class)
abstract class ReportDataBase : RoomDatabase() {
    abstract fun reportDao(): ReportDao
}