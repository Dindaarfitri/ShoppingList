package com.example.shoppinglist.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import kotlinx.coroutines.delay
import androidx.compose.ui.graphics.graphicsLayer


@Composable
fun ShoppingList(items: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items, key = { it }) { item ->
            AnimatedShoppingListItem(item = item)
        }
    }
}

@Composable
fun AnimatedShoppingListItem(item: String) {
    var isVisible by remember { mutableStateOf(false) }

    // Trigger animasi saat item pertama kali muncul
    LaunchedEffect(item) {
        delay(100)
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it }, // muncul dari bawah
            animationSpec = tween(600)
        ) + fadeIn(animationSpec = tween(600))
    ) {
        ShoppingListItem(item)
    }
}

@Composable
fun ShoppingListItem(item: String) {
    var isSelected by remember { mutableStateOf(false) }

    // Animasi warna background
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surface
        },
        animationSpec = tween(300),
        label = "backgroundColor"
    )

    // Animasi warna teks & ikon
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.onPrimaryContainer
        } else {
            MaterialTheme.colorScheme.onSurface
        },
        animationSpec = tween(300),
        label = "contentColor"
    )

    // Animasi elevasi
    val elevation by animateDpAsState(
        targetValue = if (isSelected) 8.dp else 4.dp,
        animationSpec = tween(300),
        label = "elevation"
    )

    // Animasi rotasi ikon
    val rotation by animateFloatAsState(
        targetValue = if (isSelected) 360f else 0f,
        animationSpec = tween(500),
        label = "rotation"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = elevation,
                shape = RoundedCornerShape(16.dp),
                clip = false
            )
            .clickable { isSelected = !isSelected },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = if (isSelected) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            } else {
                MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Kotak huruf pertama
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .shadow(2.dp, shape = RoundedCornerShape(50))
                    .background(
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(50)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.firstOrNull()?.uppercase() ?: "?",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            // Nama item
            Text(
                text = item,
                style = MaterialTheme.typography.titleMedium,
                color = contentColor,
                modifier = Modifier.weight(1f)
            )

            // Icon status dengan animasi rotasi
            Surface(
                modifier = Modifier
                    .size(32.dp)
                    .graphicsLayer { rotationZ = rotation },
                shape = RoundedCornerShape(8.dp),
                color = if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                }
            ) {
                Icon(
                    imageVector = if (isSelected) Icons.Default.Check else Icons.Default.Add,
                    contentDescription = if (isSelected) "Selected" else "Add to cart",
                    tint = if (isSelected) {
                        MaterialTheme.colorScheme.onPrimary
                    } else {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    },
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingListPreview() {
    ShoppingListTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ShoppingList(
                items = listOf("Susu Segar", "Roti Tawar", "Telur Ayam", "Apel Fuji", "Daging Sapi")
            )
        }
    }
}
