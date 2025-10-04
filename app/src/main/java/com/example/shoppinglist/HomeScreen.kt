package com.example.shoppinglist.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.shoppinglist.components.ShoppingList

@Composable
fun HomeScreen() {
    Surface {
        ShoppingList(
            items = listOf("Susu Segar", "Roti Tawar", "Telur Ayam", "Apel Fuji", "Daging Sapi")
        )
    }
}
