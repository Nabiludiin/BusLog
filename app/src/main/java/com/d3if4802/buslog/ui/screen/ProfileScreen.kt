package com.d3if4802.buslog.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.d3if4802.buslog.R

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.profile_name),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(R.string.profile_email),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.profile_major),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = stringResource(R.string.profile_university),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    com.d3if4802.buslog.ui.theme.BusLogTheme {
        ProfileScreen()
    }
}