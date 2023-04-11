package com.gawit.openweather.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gawit.openweather.dao.CityDao
import com.gawit.openweather.model.City

@Database(entities = [City::class], version = 1)
abstract class OpenWeatherDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object {
        @Volatile
        private var INSTANCE: OpenWeatherDatabase? = null

        fun getDatabase(context: Context): OpenWeatherDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OpenWeatherDatabase::class.java,
                    "daily_task_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}