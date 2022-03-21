package com.example.busschedule.database.schedule

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
* モデル、DAO、データベースのセットアップを行うクラス
* */
@Database(entities = arrayOf(Schedule::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    /*
    * スケジュールDaoを返す（他のクラスがDaoにアクセスできるように）
    * */
    abstract fun scheduleDao(): ScheduleDao

    /*
    * 既存のAppDatabaseインスタンスを返すメソッド、または初めてデータベースを作成
    * */
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .createFromAsset("database/bus_schedule.db")
                    .build()
                INSTANCE = instance

                instance
            }

        }
    }
}