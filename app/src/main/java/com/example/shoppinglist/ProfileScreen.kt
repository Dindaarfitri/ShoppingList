package com.example.shoppinglist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.R

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Foto profil
                Image(
                    painter = painterResource(id = R.drawable.my_profile_pic),
                    contentDescription = "Foto Diri",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(8.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Data diri
                Text("Nama: Dinda Arfitri", style = MaterialTheme.typography.titleMedium)
                Text("NIM: 2311521002", style = MaterialTheme.typography.bodyMedium)
                Text("Hobi: Design & Traveling", style = MaterialTheme.typography.bodyMedium)
                Text("TTL: Pasaman, 01 Oktober 2004", style = MaterialTheme.typography.bodyMedium)
                Text("Peminatan: Mobile Programming", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
