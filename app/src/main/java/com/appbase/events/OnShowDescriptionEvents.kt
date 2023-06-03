package com.appbase.events

import com.appbase.redux.ApplicationState
import javax.inject.Inject

class OnShowDescriptionEvents @Inject constructor() {
    fun invoke(state: ApplicationState, selectedId: Int): ApplicationState {
        val currentFavIds = state.showDescriptionIds
        val descriptionIds = if (currentFavIds.contains(selectedId)) {
            currentFavIds.filter { it != selectedId }.toSet()
        } else currentFavIds + setOf(selectedId)
        return state.copy(
            showDescriptionIds = descriptionIds
        )
    }
}