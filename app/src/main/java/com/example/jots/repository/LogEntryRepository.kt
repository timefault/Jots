package com.example.jots.repository

import androidx.lifecycle.LiveData
import com.example.jots.data.LogEntryDao
import com.example.jots.model.LogEntry
import kotlinx.coroutines.flow.Flow

class LogEntryRepository(private val logEntryDao: LogEntryDao) {

//    val allEntries: Flow<List<LogEntry>> = logEntryDao.getAllEntries()

    fun getAllEntries(): Flow<List<LogEntry>> {
        return logEntryDao.getAllEntries()
    }

    suspend fun addEntry(entry: LogEntry) {
        logEntryDao.addEntry(entry)
    }
    suspend fun updateEntry(entry: LogEntry) {
        logEntryDao.updateEntry(entry)
    }
    suspend fun deleteEntry(entry: LogEntry) {
        logEntryDao.deleteEntry(entry)
    }
    suspend fun deleteAllEntries() {
        logEntryDao.deleteAllEntries()
    }
    fun searchDatabase(searchQuery: String): Flow<List<LogEntry>> {
        return logEntryDao.searchDatabase(searchQuery)
    }
}