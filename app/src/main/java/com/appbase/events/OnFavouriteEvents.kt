package com.appbase.events

import com.appbase.redux.ApplicationState
import javax.inject.Inject

class OnFavouriteEvents @Inject constructor() {
    fun invoke(state: ApplicationState, selectedId: Int): ApplicationState {
        val currentFavIds = state.favouriteProductIds
        val neeFavIds = if (currentFavIds.contains(selectedId)) {
            currentFavIds.filter { it != selectedId }.toSet()
        } else currentFavIds + setOf(selectedId)
        return state.copy(
            favouriteProductIds = neeFavIds
        )
    }
}