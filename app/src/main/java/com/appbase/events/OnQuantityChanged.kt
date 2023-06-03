package com.appbase.events

import com.appbase.redux.ApplicationState
import javax.inject.Inject


class OnQuantityChanged @Inject constructor() {
    fun invoke(
        currentState: ApplicationState,
        productId: Int,
        newQuantity: Int,
    ): ApplicationState {
        if (newQuantity < 1) return currentState
        val currentQuantitiesMap = currentState.cartQuantitiesMap
        val newQuantitiesMap = currentQuantitiesMap + (productId to newQuantity)
        return currentState.copy(cartQuantitiesMap = newQuantitiesMap)

    }
}