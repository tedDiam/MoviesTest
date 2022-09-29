package com.sergediame.moviesyassirtest.navigation

import com.sergediame.moviesyassirtest.navigation.NavigationCommand
import kotlinx.coroutines.flow.Flow

interface NavigationManager {
    val navigationEvent: Flow<NavigationCommand>
    fun navigate(command: NavigationCommand)
}
