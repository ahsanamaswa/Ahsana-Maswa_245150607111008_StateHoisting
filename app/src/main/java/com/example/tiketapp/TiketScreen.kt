package com.example.tiketapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicketScreen(
    modifier: Modifier = Modifier,
    namaPembeli: String,
    jumlahTiket: Int,
    hargaTiket: Int,
    status: String,
    isProcessing: Boolean,
    onNamaChange: (String) -> Unit,
    onJumlahTambah: () -> Unit,
    onJumlahKurang: () -> Unit,
    onPesanClick: () -> Unit
) {
    val biru = Color(0xFF1565C0)

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Pemesanan Tiket", fontSize = 22.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Nama")
        OutlinedTextField(
            value = namaPembeli,
            onValueChange = onNamaChange,
            placeholder = { Text("Masukkan nama Anda") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Jumlah Tiket")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = onJumlahKurang,
                colors = ButtonDefaults.buttonColors(containerColor = biru)
            ) {
                Text("-")
            }
            Text(text = "$jumlahTiket")
            Button(
                onClick = onJumlahTambah,
                colors = ButtonDefaults.buttonColors(containerColor = biru)
            ) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Harga per tiket: Rp$hargaTiket")
        Text(text = "Total: Rp${hargaTiket * jumlahTiket}")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onPesanClick,
            enabled = !isProcessing,
            colors = ButtonDefaults.buttonColors(containerColor = biru),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isProcessing) "Memproses..." else "Pesan Tiket")
        }

        Spacer(modifier = Modifier.height(16.dp))

        val statusColor = when {
            status.contains("telah dipesan") -> Color(0xFF2E7D32) // hijau
            status.contains("Nama Masih Kosong") || status.contains("harus diisi") -> Color(0xFFD32F2F) // merah
            else -> Color.Unspecified
        }

        Text(
            text = "Status: $status",
            color = statusColor
        )
    }
}