package com.appbase.redux

data class ApplicationState(
    val favouriteProductIds: Set<Int> = emptySet(),
    val showDescriptionIds: Set<Int> = emptySet(),
    val cartProductIds: Set<Int> = emptySet(),
    val cartQuantitiesMap: Map<Int, Int> = emptyMap(),
)
