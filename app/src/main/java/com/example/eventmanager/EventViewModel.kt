package com.example.eventmanager

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EventViewModel(application: Application) : AndroidViewModel(application) {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    //ToDo removed .asLiveData() from the below code
    private val repository: EventRepository
     val allEvents: LiveData<List<Event>>
    init {
        val dao = EventDatabase.getDatabase(application).getEventDao()
        repository = EventRepository(dao)
        allEvents = repository.allEvents
    }
    fun insert(event: Event) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(event)
    }

    fun delete(event: Event) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(event)
    }
}