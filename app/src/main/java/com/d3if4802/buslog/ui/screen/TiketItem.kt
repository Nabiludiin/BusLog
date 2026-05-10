package com.d3if4802.buslog.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.d3if4802.buslog.model.TiketBus

@Composable
fun TiketItem(tiket: TiketBus, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = tiket.poBus, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(
                    text = "ID: ${tiket.id}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(text = "From", fontSize = 12.sp, color = Color.Gray)
                    Text(text = tiket.asal, fontWeight = FontWeight.Medium)
                }
                Text(text = " ➔ ", modifier = Modifier.padding(horizontal = 50.dp))
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "To", fontSize = 12.sp, color = Color.Gray)
                    Text(text = tiket.tujuan, fontWeight = FontWeight.Medium)
                }
            }
            Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "Date", fontSize = 12.sp, color = Color.Gray)
                    Text(text = tiket.tanggal)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "Seat", fontSize = 12.sp, color = Color.Gray)
                    Text(text = tiket.nomorKursi, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun TiketPreview() {
    val dummyTiket = com.d3if4802.buslog.model.TiketBus(
        id = 1,
        poBus = "Sinar Jaya",
        asal = "Bandung",
        tujuan = "Palembang",
        tanggal = "20 Mei 2026",
        harga = 350000,
        nomorKursi = "12A"
    )

    com.d3if4802.buslog.ui.theme.BusLogTheme {
        TiketItem(tiket = dummyTiket, onClick = {})
    }
}