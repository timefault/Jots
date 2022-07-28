package com.example.jots.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.jots.model.LogEntry
import com.example.jots.data.LogEntryDatabase
import com.example.jots.repository.LogEntryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogEntryViewModel(application: Application): AndroidViewModel(application) {

    private val repository: LogEntryRepository
//    var allEntries: LiveData<List<LogEntry>>
    init {
        val logEntryDao = LogEntryDatabase.getDatabase(application).logEntryDao()
        repository = LogEntryRepository(logEntryDao)
//        allEntries = repository.allEntries
//        allEntries = repository.getAllEntries().asLiveData()
    }
    fun getAllEntries():LiveData<List<LogEntry>> {
        return repository.getAllEntries().asLiveData()
    }
    fun addEntry(logEntry: LogEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEntry(logEntry)
        }
    }
    fun updateEntry(logEntry: LogEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEntry(logEntry)
        }
    }
    fun deleteEntry(logEntry: LogEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEntry(logEntry)
        }
    }
    fun deleteAllEntries() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllEntries()
        }
    }
    fun searchDatabase(searchQuery: String): LiveData<List<LogEntry>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }
}