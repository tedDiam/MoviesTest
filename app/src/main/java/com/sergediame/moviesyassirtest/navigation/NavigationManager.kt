package com.sergediame.moviesyassirtest.navigation

import androidx.navigation.NamedNavArgument
import kotlinx.coroutines.flow.Flow

interface NavigationManager {
    val navigationEvent: Flow<NavigationCommand>
    fun navigate(command: NavigationCommand)
}
