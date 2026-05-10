package com.d3if4802.buslog.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.d3if4802.buslog.R
import com.d3if4802.buslog.model.TiketBus

@Composable
fun TiketItem(
    tiket: TiketBus,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(stringResource(id = R.string.delete_title)) },
            text = { Text(stringResource(id = R.string.delete_message)) },
            confirmButton = {
                TextButton(onClick = {
                    onDelete()
                    showDialog = false
                }) {
                    Text(stringResource(id = R.string.delete), color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(stringResource(id = R.string.cancel))
                }
            }
        )
    }

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = tiket.poBus, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                IconButton(onClick = { showDialog = true }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = Color.White)
                }
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = stringResource(R.string.origin_label), fontSize = 10.sp, color = Color.Gray)
                        Text(text = tiket.asal, fontWeight = FontWeight.SemiBold)
                    }
                    Text(text = "➔", color = MaterialTheme.colorScheme.primary)
                    Column(horizontalAlignment = Alignment.End) {
                        Text(text = stringResource(R.string.destination_label), fontSize = 10.sp, color = Color.Gray)
                        Text(text = tiket.tujuan, fontWeight = FontWeight.SemiBold)
                    }
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), thickness = 0.5.dp)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text(text = stringResource(R.string.date_label), fontSize = 10.sp, color = Color.Gray)
                        Text(text = tiket.tanggal, fontSize = 14.sp)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(text = stringResource(R.string.seat_label), fontSize = 10.sp, color = Color.Gray)
                        Text(text = tiket.nomorKursi, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}