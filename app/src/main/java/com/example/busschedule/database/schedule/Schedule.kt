package com.example.busschedule.database.schedule

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* バスのスケジュールのEntity(Roomが手キーやカラム名を識別するアノテーションを記載、NotNull制約のカラムにも)
* */
@Entity
data class Schedule(
    @PrimaryKey val id: Int,

    @NonNull
    @ColumnInfo(name = "stop_name")
    val stopName: String,

    @NonNull
    @ColumnInfo(name = "arrival_time")
    val arrivalTime: Int
)
