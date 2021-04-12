package com.example.eventmanager

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events_table")
class Event(@PrimaryKey(autoGenerate = true)var id: Int = 0,
            @ColumnInfo(name = "Title")val header: String ="Abhishek",
            @ColumnInfo(name = "Text")val text: String="sample text"){
}