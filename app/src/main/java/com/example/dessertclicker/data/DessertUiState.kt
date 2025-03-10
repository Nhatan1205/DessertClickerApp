package com.example.dessertclicker.data

import androidx.annotation.DrawableRes

data class DessertUiState(
    val revenue: Int = 0,
    val firstDessert: DessertState = DessertState(
        dessertsSold = 0,
        currentDessertIndex = 0,
        currentDessertImageId = Datasource.dessertList[0].imageId,
        currentDessertPrice = Datasource.dessertList[0].price
    ),
    val secondDessert: DessertState = DessertState(
        dessertsSold = 0,
        currentDessertIndex = 1, // Start with the second dessert in the list
        currentDessertImageId = Datasource.dessertList[1].imageId,
        currentDessertPrice = Datasource.dessertList[1].price
    )
)

data class DessertState(
    val dessertsSold: Int,
    val currentDessertIndex: Int,
    @DrawableRes val currentDessertImageId: Int,
    val currentDessertPrice: Int
)