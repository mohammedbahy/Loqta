package com.bahy.loqta.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bahy.loqta.ui.components.LoqtaTopBar
import com.bahy.loqta.ui.components.SearchBar
import com.bahy.loqta.ui.theme.LoqtaGreen
import com.bahy.loqta.ui.theme.LoqtaGreenDark

private data class Conversation(
    val id: String,
    val name: String,
    val preview: String,
    val time: String,
    val unread: Int = 0,
    val isOnline: Boolean = false,
    val avatarColor: Color,
)

private val filters = listOf("الكل", "شراء", "بيع")
private val conversations = listOf(
    Conversation("1", "أحمد المحمد", "هل السعر النهائي يشمل التوصيل؟", "الآن", 2, true, Color(0xFF5C6BC0)),
    Conversation("2", "سارة علي", "متى يمكنني الاستلام؟", "١٠:٣٠ ص", 0, false, Color(0xFF26A69A)),
    Conversation("3", "محمد خالد", "هل المنتج متوفر؟", "أمس", 0, false, Color(0xFFEF5350)),
    Conversation("4", "فاطمة أحمد", "شكراً على التعامل", "٢٢/٠٥", 0, false, Color(0xFFAB47BC)),
)

@Composable
fun ConversationsScreen(
    onNavigate: (String) -> Unit,
    onChatClick: (String) -> Unit = {},
) {
    var query by remember { androidx.compose.runtime.mutableStateOf("") }
    var selectedFilter by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = { LoqtaTopBar() },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            SearchBar(
                query = query,
                onQueryChange = { query = it },
                placeholder = "ابحث في الرسائل...",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(filters.size) { index ->
                    FilterChip(
                        selected = selectedFilter == index,
                        onClick = { selectedFilter = index },
                        label = { Text(filters[index]) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = LoqtaGreenDark,
                            selectedLabelColor = Color.White,
                            containerColor = Color(0xFFE3F2FD),
                        ),
                    )
                }
            }

            Spacer(modifier = Modifier.size(8.dp))

            LazyColumn {
                items(conversations) { conversation ->
                    ConversationRow(
                        conversation = conversation,
                        onClick = { onChatClick(conversation.id) },
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                }
            }
        }
    }
}

@Composable
private fun ConversationRow(
    conversation: Conversation,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(conversation.avatarColor),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    conversation.name.first().toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            if (conversation.isOnline) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(14.dp)
                        .clip(CircleShape)
                        .background(LoqtaGreen),
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(conversation.name, fontWeight = FontWeight.Bold)
            Text(
                conversation.preview,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                conversation.time,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            if (conversation.unread > 0) {
                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .size(22.dp)
                        .clip(CircleShape)
                        .background(LoqtaGreenDark),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        conversation.unread.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}
