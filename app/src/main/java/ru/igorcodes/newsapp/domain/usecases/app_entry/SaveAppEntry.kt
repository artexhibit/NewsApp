package ru.igorcodes.newsapp.domain.usecases.app_entry

import ru.igorcodes.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}