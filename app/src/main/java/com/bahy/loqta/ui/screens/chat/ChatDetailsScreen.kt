package com.bahy.loqta.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bahy.loqta.ui.theme.LoqtaGreen
import com.bahy.loqta.ui.theme.LoqtaGreenDark

private data class ChatMessage(
    val text: String,
    val isOutgoing: Boolean,
    val time: String,
    val hasImage: Boolean = false,
)

private val messages = listOf(
    ChatMessage("وعليكم السلام، نعم الجهاز متوفر وبحالة الوكالة.", isOutgoing = true, time = "10:32 ص"),
    ChatMessage("هل ممكن صور إضافية لزوايا الجهاز؟", isOutgoing = false, time = "10:35 ص", hasImage = true),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatDetailsScreen(
    onBack: () -> Unit = {},
    onViewProduct: () -> Unit = {},
) {
    var message by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF5C6BC0)),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text("أ", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Column(modifier = Modifier.padding(start = 12.dp)) {
                            Text("أحمد العتيبي", fontWeight = FontWeight.Bold)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(LoqtaGreen))
                                Text("  متصل الآن", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "رجوع")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.MoreVert, contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface),
            )
        },
        bottomBar = {
            ChatInputBar(
                message = message,
                onMessageChange = { message = it },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF5F7F9)),
        ) {
            ProductBanner(onViewProduct = onViewProduct)

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFE3F2FD),
                ) {
                    Text("اليوم", modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp), style = MaterialTheme.typography.labelSmall)
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(messages) { msg ->
                    MessageBubble(msg)
                }
            }
        }
    }
}

@Composable
private fun ProductBanner(onViewProduct: () -> Unit) {
    Surface(color = Color(0xFFEEF4FF)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF263238)),
            )
            Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                Text("آيفون 15 برو ماكس - 256 جيجا", fontWeight = FontWeight.Medium, maxLines = 1)
                Text("3,500 ريال", color = LoqtaGreen, fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = onViewProduct,
                colors = ButtonDefaults.buttonColors(containerColor = LoqtaGreenDark),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text("عرض")
            }
        }
    }
}

@Composable
private fun MessageBubble(message: ChatMessage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isOutgoing) Alignment.End else Alignment.Start,
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = if (message.isOutgoing) LoqtaGreen else Color.White,
            modifier = Modifier.widthIn(max = if (message.hasImage) 260.dp else 280.dp),
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                if (message.hasImage) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFECEFF1)),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Text(
                    message.text,
                    color = if (message.isOutgoing) Color.White else MaterialTheme.colorScheme.onSurface,
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 4.dp),
        ) {
            if (message.isOutgoing) {
                Icon(Icons.Default.DoneAll, contentDescription = null, tint = Color(0xFF2196F3), modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
            }
            Text(message.time, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun ChatInputBar(
    message: String,
    onMessageChange: (String) -> Unit,
) {
    Surface(shadowElevation = 8.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(LoqtaGreenDark),
            ) {
                Icon(Icons.Default.Mic, contentDescription = null, tint = Color.White)
            }
            OutlinedTextField(
                value = message,
                onValueChange = onMessageChange,
                placeholder = { Text("اكتب رسالتك...") },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(24.dp),
                leadingIcon = { Icon(Icons.Default.EmojiEmotions, contentDescription = null) },
            )
            IconButton(onClick = {}) {
                Icon(Icons.Default.CameraAlt, contentDescription = null)
            }
            IconButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    }
}
