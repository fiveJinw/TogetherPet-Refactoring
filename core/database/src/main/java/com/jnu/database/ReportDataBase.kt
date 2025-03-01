package com.jnu.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jnu.database.dao.ReportDao
import com.jnu.database.model.ReportEntity
import com.jnu.database.util.TypeConverterModule

@Database(entities = [com.jnu.database.model.ReportEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverterModule::class)
abstract class ReportDataBase : RoomDatabase() {
    abstract fun reportDao(): com.jnu.database.dao.ReportDao
}