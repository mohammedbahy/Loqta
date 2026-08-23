package com.bahy.loqta.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bahy.loqta.ui.components.LoqtaBottomBar
import com.bahy.loqta.ui.components.LoqtaTopBar
import com.bahy.loqta.ui.theme.LoqtaGreen
import com.bahy.loqta.ui.theme.LoqtaGreenDark
import com.bahy.loqta.ui.theme.LoqtaGreenLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyProfileScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    onEditProfile: () -> Unit = {},
) {
    Scaffold(
        topBar = { LoqtaTopBar() },
        bottomBar = {
            LoqtaBottomBar(currentRoute = currentRoute, onItemClick = { onNavigate(it.route) })
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
        ) {
            ProfileHeader(onEditProfile = onEditProfile, onAvatarClick = onEditProfile)
            StatsRow()
            MenuSection(onNavigate = onNavigate)
            UpgradeBanner()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ProfileHeader(onEditProfile: () -> Unit, onAvatarClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(LoqtaGreen, LoqtaGreenLight.copy(alpha = 0.3f), Color.White),
                ),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box {
                Box(
                    modifier = Modifier
                        .size(88.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF5C6BC0))
                        .border(3.dp, Color.White, CircleShape)
                        .clickable(onClick = onAvatarClick),
                    contentAlignment = Alignment.Center,
                ) {
                    Text("أ", color = Color.White, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(LoqtaGreen),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Default.Verified, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text("أحمد المنصور", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = LoqtaGreenDark)
            Row(
                modifier = Modifier.padding(top = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Box(
                    modifier = Modifier
                        .background(LoqtaGreen.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                ) {
                    Text("بائع موثوق", color = LoqtaGreenDark, style = MaterialTheme.typography.labelLarge)
                }
                Text(
                    "انضم منذ سبتمبر 2023",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun StatsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .offset(y = (-20).dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        StatCard("42", "منتج مباع", Modifier.weight(1f))
        RatingStatCard(Modifier.weight(1f))
        StatCard("156", "متابعين", Modifier.weight(1f))
    }
}

@Composable
private fun RatingStatCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("4.9", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = LoqtaGreenDark)
                Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFE53935), modifier = Modifier.size(18.dp).padding(start = 2.dp))
            }
            Text("التقييم", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun StatCard(value: String, label: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = LoqtaGreenDark)
            Text(label, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun MenuSection(onNavigate: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            MenuItem("إعلاناتي", Icons.Default.Inventory2, LoqtaGreen.copy(alpha = 0.15f)) { onNavigate("my_products") }
            MenuItem("تقييماتي", Icons.Default.RateReview, LoqtaGreen.copy(alpha = 0.15f)) { onNavigate("my_reviews") }
            MenuItem("سجل المشتريات", Icons.Default.History, LoqtaGreen.copy(alpha = 0.15f)) { onNavigate("purchase_history") }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            MenuItem("الإعدادات", Icons.Default.Settings, Color(0xFFE3F2FD)) { onNavigate("settings") }
            MenuItem("مركز المساعدة", Icons.Default.HelpOutline, Color(0xFFE3F2FD)) { onNavigate("help_center") }
            MenuItem("تسجيل الخروج", Icons.Default.Logout, Color(0xFFFFEBEE), isDestructive = true) {}
        }
    }
}

@Composable
private fun MenuItem(
    label: String,
    icon: ImageVector,
    iconBg: Color,
    isDestructive: Boolean = false,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            label,
            modifier = Modifier.weight(1f).padding(horizontal = 12.dp),
            fontWeight = FontWeight.Medium,
            color = if (isDestructive) Color(0xFFE53935) else MaterialTheme.colorScheme.onSurface,
        )
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(iconBg),
            contentAlignment = Alignment.Center,
        ) {
            Icon(icon, contentDescription = null, tint = if (isDestructive) Color(0xFFE53935) else LoqtaGreenDark, modifier = Modifier.size(20.dp))
        }
    }
}

@Composable
private fun UpgradeBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A237E)),
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("قم بترقية حسابك", color = Color.White, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(
                    "احصل على تمييز لإعلاناتك ووصول أسرع للمشترين المميزين.",
                    color = Color.White.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
            TextButton(
                onClick = {},
                modifier = Modifier.background(LoqtaGreenLight, RoundedCornerShape(20.dp)),
            ) {
                Text("اشترك الآن", color = LoqtaGreenDark, fontWeight = FontWeight.Bold)
            }
        }
    }
}
