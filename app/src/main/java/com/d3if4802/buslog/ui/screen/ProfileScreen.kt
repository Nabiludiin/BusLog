package com.d3if4802.buslog.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.d3if4802.buslog.R
import com.d3if4802.buslog.database.TiketDb
import com.d3if4802.buslog.util.SettingsDataStore
import com.d3if4802.buslog.util.ViewModelFactory

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    val db = TiketDb.getInstance(context)
    val dataStore = SettingsDataStore(context)
    val factory = ViewModelFactory(db.dao, dataStore)
    val viewModel: MainViewModel = viewModel(factory = factory)

    val profile by viewModel.profileData.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        ProfileEditDialog(
            onDismiss = { showDialog = false },
            onConfirm = { nama, email, nim ->
                viewModel.upsertProfile(nama, email, nim)
                showDialog = false
            },
            currentNama = profile?.nama ?: stringResource(R.string.profile_name),
            currentEmail = profile?.email ?: stringResource(R.string.profile_email),
            currentNim = profile?.nim ?: stringResource(R.string.profile_major)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = profile?.nama ?: stringResource(R.string.profile_name),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = profile?.email ?: stringResource(R.string.profile_email),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = profile?.nim ?: stringResource(R.string.profile_major),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = stringResource(R.string.profile_university),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { showDialog = true }) {
            Text(text = "Edit Profil")
        }
    }
}

@Composable
fun ProfileEditDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, String) -> Unit,
    currentNama: String,
    currentEmail: String,
    currentNim: String
) {
    var nama by remember { mutableStateOf(currentNama) }
    var email by remember { mutableStateOf(currentEmail) }
    var nim by remember { mutableStateOf(currentNim) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Profil") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = nama, onValueChange = { nama = it }, label = { Text("Nama") })
                OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
                OutlinedTextField(value = nim, onValueChange = { nim = it }, label = { Text("NIM / Prodi") })
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(nama, email, nim) }) {
                Text(stringResource(R.string.save))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    com.d3if4802.buslog.ui.theme.BusLogTheme {
        ProfileScreen()
    }
}