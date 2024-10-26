package ru.igorcodes.newsapp.domain.usecases.app_entry

import kotlinx.coroutines.flow.Flow
import ru.igorcodes.newsapp.domain.manager.LocalUserManager

class ReadAppEntry(private val localUserManager: LocalUserManager) {
    operator fun invoke() : Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}