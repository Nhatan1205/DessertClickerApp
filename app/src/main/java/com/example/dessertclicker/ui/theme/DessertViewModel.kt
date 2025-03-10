package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.DessertUiState
import com.example.dessertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {

    // MutableStateFlow to hold the UI state
    private val _dessertUiState = MutableStateFlow(DessertUiState())
    val dessertUiState: StateFlow<DessertUiState> = _dessertUiState.asStateFlow()

    // Handle click for the first dessert
    fun onFirstDessertClicked() {
        _dessertUiState.update { currentState ->
            val dessertsSold = currentState.firstDessert.dessertsSold + 1
            val nextDessertIndex = determineDessertIndex(dessertsSold)

            currentState.copy(
                revenue = currentState.revenue + currentState.firstDessert.currentDessertPrice,
                firstDessert = currentState.firstDessert.copy(
                    dessertsSold = dessertsSold,
                    currentDessertIndex = nextDessertIndex,
                    currentDessertImageId = dessertList[nextDessertIndex].imageId,
                    currentDessertPrice = dessertList[nextDessertIndex].price
                )
            )
        }
    }

    // Handle click for the second dessert
    fun onSecondDessertClicked() {
        _dessertUiState.update { currentState ->
            val dessertsSold = currentState.secondDessert.dessertsSold + 1
            val nextDessertIndex = determineDessertIndex(dessertsSold)

            currentState.copy(
                revenue = currentState.revenue + currentState.secondDessert.currentDessertPrice,
                secondDessert = currentState.secondDessert.copy(
                    dessertsSold = dessertsSold,
                    currentDessertIndex = nextDessertIndex,
                    currentDessertImageId = dessertList[nextDessertIndex].imageId,
                    currentDessertPrice = dessertList[nextDessertIndex].price
                )
            )
        }
    }

    // Determine the next dessert index based on the number of desserts sold
    private fun determineDessertIndex(dessertsSold: Int): Int {
        var dessertIndex = 0
        for (index in dessertList.indices) {
            if (dessertsSold >= dessertList[index].startProductionAmount) {
                dessertIndex = index
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more
                // desserts, you'll start producing more expensive desserts as determined by
                // startProductionAmount. We know to break as soon as we see a dessert who's
                // "startProductionAmount" is greater than the amount sold.
                break
            }
        }
        return dessertIndex
    }
}