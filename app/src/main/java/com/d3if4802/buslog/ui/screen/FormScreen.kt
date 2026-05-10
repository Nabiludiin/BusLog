package com.d3if4802.buslog.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.d3if4802.buslog.R
import com.d3if4802.buslog.database.TiketDb
import com.d3if4802.buslog.model.TiketBus
import com.d3if4802.buslog.util.SettingsDataStore
import com.d3if4802.buslog.util.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(navController: NavHostController, tiketId: Int? = null) {
    val context = LocalContext.current
    val db = TiketDb.getInstance(context)
    val dataStore = SettingsDataStore(context)
    val factory = ViewModelFactory(db.dao, dataStore)
    val viewModel: MainViewModel = viewModel(factory = factory)

    val listData by viewModel.tiketData.collectAsState()
    val tiketLama = listData.find { it.id == tiketId }

    var poBus by remember { mutableStateOf("") }
    var asal by remember { mutableStateOf("") }
    var tujuan by remember { mutableStateOf("") }
    var tanggal by remember { mutableStateOf("") }
    var harga by remember { mutableStateOf("") }
    var nomorKursi by remember { mutableStateOf("") }

    LaunchedEffect(tiketLama) {
        if (tiketLama != null) {
            poBus = tiketLama.poBus
            asal = tiketLama.asal
            tujuan = tiketLama.tujuan
            tanggal = tiketLama.tanggal
            harga = tiketLama.harga.toString()
            nomorKursi = tiketLama.nomorKursi
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(if (tiketId == null) R.string.add_ticket else R.string.edit_ticket))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (poBus.isEmpty() || asal.isEmpty()) return@IconButton
                        val tiket = TiketBus(
                            id = tiketId ?: 0,
                            poBus = poBus,
                            asal = asal,
                            tujuan = tujuan,
                            tanggal = tanggal,
                            harga = harga.toIntOrNull() ?: 0,
                            nomorKursi = nomorKursi
                        )
                        if (tiketId == null) viewModel.insertTiket(tiket)
                        else viewModel.updateTiket(tiket)
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        FormContent(
            modifier = Modifier.padding(paddingValues),
            poBus = poBus, onPoChange = { poBus = it },
            asal = asal, onAsalChange = { asal = it },
            tujuan = tujuan, onTujuanChange = { tujuan = it },
            tanggal = tanggal, onTanggalChange = { tanggal = it },
            harga = harga, onHargaChange = { harga = it },
            nomorKursi = nomorKursi, onKursiChange = { nomorKursi = it }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormContent(
    modifier: Modifier = Modifier,
    poBus: String, onPoChange: (String) -> Unit,
    asal: String, onAsalChange: (String) -> Unit,
    tujuan: String, onTujuanChange: (String) -> Unit,
    tanggal: String, onTanggalChange: (String) -> Unit,
    harga: String, onHargaChange: (String) -> Unit,
    nomorKursi: String, onKursiChange: (String) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    val selectedDate = datePickerState.selectedDateMillis
                    if (selectedDate != null) {
                        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        onTanggalChange(formatter.format(Date(selectedDate)))
                    }
                    showDatePicker = false
                }) { Text(stringResource(R.string.save)) }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) { Text(stringResource(R.string.cancel)) }
            }
        ) { DatePicker(state = datePickerState) }
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = poBus,
            onValueChange = onPoChange,
            label = { Text(stringResource(R.string.po_bus_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = asal,
                onValueChange = onAsalChange,
                label = { Text(stringResource(R.string.origin_label)) },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = tujuan,
                onValueChange = onTujuanChange,
                label = { Text(stringResource(R.string.destination_label)) },
                modifier = Modifier.weight(1f)
            )
        }

        OutlinedTextField(
            value = tanggal,
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(R.string.date_label)) },
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(Icons.Default.DateRange, contentDescription = null)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDatePicker = true }
        )

        OutlinedTextField(
            value = harga,
            onValueChange = onHargaChange,
            label = { Text(stringResource(R.string.price_label)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nomorKursi,
            onValueChange = onKursiChange,
            label = { Text(stringResource(R.string.seat_label)) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FormPreview() {
    com.d3if4802.buslog.ui.theme.BusLogTheme {
        FormContent(
            poBus = "Sinar Jaya", onPoChange = {},
            asal = "Bandung", onAsalChange = {},
            tujuan = "Palembang", onTujuanChange = {},
            tanggal = "11/05/2026", onTanggalChange = {},
            harga = "350000", onHargaChange = {},
            nomorKursi = "12A", onKursiChange = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FormFullPreview() {
    val navController = rememberNavController()
    com.d3if4802.buslog.ui.theme.BusLogTheme {
        FormScreen(navController = navController)
    }
}