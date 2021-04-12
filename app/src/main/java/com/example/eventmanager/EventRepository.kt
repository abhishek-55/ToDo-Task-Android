package com.example.eventmanager

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData


class EventRepository(private val eventDao: EventDao) {

    val allEvents: LiveData<List<Event>> = eventDao.getEvents()

    suspend fun insert(event: Event) {
        eventDao.insert(event)
    }

    suspend fun delete(event: Event) {
        eventDao.delete(event)
    }
}