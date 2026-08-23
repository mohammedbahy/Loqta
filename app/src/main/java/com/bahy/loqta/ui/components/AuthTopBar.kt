package com.bahy.loqta.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.bahy.loqta.ui.theme.LoqtaGreenDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTopBar(
    onBack: (() -> Unit)? = null,
    showClose: Boolean = false,
    onClose: () -> Unit = {},
    showHelp: Boolean = false,
    onHelp: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Loqta",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = LoqtaGreenDark,
            )
        },
        navigationIcon = {
            when {
                showClose -> IconButton(onClick = onClose) {
                    Icon(Icons.Default.Close, contentDescription = "إغلاق")
                }
                onBack != null -> IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "رجوع")
                }
            }
        },
        actions = {
            if (showHelp) {
                IconButton(onClick = onHelp) {
                    Icon(Icons.Default.HelpOutline, contentDescription = "مساعدة")
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = Modifier.fillMaxWidth(),
    )
}
