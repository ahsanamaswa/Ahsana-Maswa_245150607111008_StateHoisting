package com.example.tiketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.tiketapp.ui.theme.TiketAppTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TiketAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TicketOrderHost(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TicketOrderHost(modifier: Modifier = Modifier) {
    var namaPembeli by rememberSaveable { mutableStateOf("") }
    var jumlahTiket by rememberSaveable { mutableStateOf(1) }
    val hargaTiket = 50000
    var status by remember { mutableStateOf("Silakan pesan tiket") }
    var isProcessing by remember { mutableStateOf(false) }

    LaunchedEffect(isProcessing) {
        if (isProcessing) {
            status = "Memproses pesanan........."
            delay(5000)
            status = "Tiket telah dipesan"
            isProcessing = false
        }
    }

    TicketScreen(
        modifier = modifier,
        namaPembeli = namaPembeli,
        jumlahTiket = jumlahTiket,
        hargaTiket = hargaTiket,
        status = status,
        isProcessing = isProcessing,
        onNamaChange = { namaPembeli = it },
        onJumlahTambah = { jumlahTiket++ },
        onJumlahKurang = { if (jumlahTiket > 1) jumlahTiket-- },
        onPesanClick = {
            if (namaPembeli.isBlank()) {
                status = "Nama harus diisi"
            } else {
                isProcessing = true
            }
        }
    )
}