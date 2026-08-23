package com.bahy.loqta.ui.screens.notifications

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bahy.loqta.ui.theme.LoqtaGreen
import com.bahy.loqta.ui.theme.LoqtaGreenDark

private enum class NotificationType(val icon: ImageVector, val color: Color) {
    MESSAGE(Icons.Default.Chat, Color(0xFF5C6BC0)),
    OFFER(Icons.Default.LocalOffer, LoqtaGreen),
    SALE(Icons.Default.ShoppingBag, Color(0xFFFF9800)),
    FAVORITE(Icons.Default.Favorite, Color(0xFFE91E63)),
    SYSTEM(Icons.Default.Verified, Color(0xFF26A69A)),
}

private data class AppNotification(
    val id: String,
    val title: String,
    val body: String,
    val time: String,
    val isRead: Boolean,
    val type: NotificationType,
)

private val filters = listOf("الكل", "غير مقروء", "العروض", "الرسائل")
private val notifications = listOf(
    AppNotification("1", "عرض جديد على منتجك", "قدم أحمد عرضاً بقيمة 300 ج.م على حذاء نايك الرياضي", "الآن", false, NotificationType.OFFER),
    AppNotification("2", "رسالة جديدة", "سارة علي: هل المنتج متاح للاستلام اليوم؟", "منذ 10 دقائق", false, NotificationType.MESSAGE),
    AppNotification("3", "تم بيع منتجك!", "تهانينا! تم بيع سماعات البلوتوث بنجاح", "منذ ساعة", true, NotificationType.SALE),
    AppNotification("4", "أضاف شخص منتجك للمفضلة", "محمد خالد أضاف كاميرتك الرقمية إلى المفضلة", "منذ 3 ساعات", true, NotificationType.FAVORITE),
    AppNotification("5", "تم توثيق حسابك", "أصبحت الآن بائعاً موثوقاً في لقطة", "أمس", true, NotificationType.SYSTEM),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(
    onBack: () -> Unit = {},
    onNotificationClick: (String) -> Unit = {},
) {
    var selectedFilter by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("الإشعارات", fontWeight = FontWeight.Bold, color = LoqtaGreenDark) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "رجوع")
                    }
                },
                actions = {
                    TextButton(onClick = {}) {
                        Text("تعليم الكل كمقروء", color = LoqtaGreen, style = MaterialTheme.typography.labelMedium)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
            )
        },
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                filters.forEachIndexed { index, filter ->
                    FilterChip(
                        selected = selectedFilter == index,
                        onClick = { selectedFilter = index },
                        label = { Text(filter) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = LoqtaGreenDark,
                            selectedLabelColor = Color.White,
                            containerColor = Color(0xFFE3F2FD),
                        ),
                    )
                }
            }

            val unreadCount = notifications.count { !it.isRead }
            if (unreadCount > 0) {
                Text(
                    "$unreadCount إشعار غير مقروء",
                    style = MaterialTheme.typography.bodySmall,
                    color = LoqtaGreen,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                )
            }

            LazyColumn(contentPadding = PaddingValues(bottom = 16.dp)) {
                items(notifications) { notification ->
                    NotificationItem(
                        notification = notification,
                        onClick = { onNotificationClick(notification.id) },
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f),
                    )
                }
            }
        }
    }
}

@Composable
private fun NotificationItem(
    notification: AppNotification,
    onClick: () -> Unit,
) {
    val bg = if (!notification.isRead) LoqtaGreen.copy(alpha = 0.04f) else Color.Transparent

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(bg)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(notification.type.color.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(notification.type.icon, contentDescription = null, tint = notification.type.color, modifier = Modifier.size(22.dp))
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    notification.title,
                    fontWeight = if (notification.isRead) FontWeight.Medium else FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                if (!notification.isRead) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(LoqtaGreen),
                    )
                }
            }
            Text(
                notification.body,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 4.dp),
            )
            Text(
                notification.time,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
    }
}
