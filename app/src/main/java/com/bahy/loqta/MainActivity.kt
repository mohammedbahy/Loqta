package com.bahy.loqta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.runtime.CompositionLocalProvider
import com.bahy.loqta.navigation.LoqtaApp
import com.bahy.loqta.ui.theme.LoqtaBackground
import com.bahy.loqta.ui.theme.LoqtaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                LoqtaTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = LoqtaBackground,
                    ) {
                        LoqtaApp()
                    }
                }
            }
        }
    }
}
