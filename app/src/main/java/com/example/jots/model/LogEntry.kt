package com.example.jots.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "log_entry_table")
data class LogEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name="content")
    val content: String,
    val le_timestamp: Long
): Parcelable
