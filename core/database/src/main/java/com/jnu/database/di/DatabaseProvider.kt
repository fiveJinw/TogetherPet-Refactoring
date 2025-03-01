package com.jnu.database.di

import android.content.Context
import androidx.room.Room
import com.jnu.database.MissingDataBase
import com.jnu.database.ReportDataBase
import com.jnu.database.WalkDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseProvider {

    @Provides
    @Singleton
    fun provideMissingDatabase(@ApplicationContext context: Context): MissingDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            MissingDataBase::class.java,
            "missing_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideReportDatabase(@ApplicationContext context: Context): ReportDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            ReportDataBase::class.java,
            "report_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WalkDataBase {
        return Room.databaseBuilder(
            context,
            WalkDataBase::class.java,
            "walking_database"
        ).build()
    }

    @Provides
    fun provideWalkDao(database: WalkDataBase): com.jnu.database.dao.WalkDao {
        return database.walkDao()
    }

    @Provides
    fun provideMissingDao(database: MissingDataBase): com.jnu.database.dao.MissingDao {
        return database.missingDao()
    }

    @Provides
    fun provideReportDao(database: ReportDataBase): com.jnu.database.dao.ReportDao {
        return database.reportDao()
    }
}