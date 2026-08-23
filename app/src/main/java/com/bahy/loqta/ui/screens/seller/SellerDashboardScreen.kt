package com.bahy.loqta.ui.screens.seller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bahy.loqta.ui.components.LoqtaBottomBar
import com.bahy.loqta.ui.components.LoqtaTopBar
import com.bahy.loqta.ui.theme.LoqtaGreen
import com.bahy.loqta.ui.theme.LoqtaGreenDark

private data class StatCard(val label: String, val value: String, val subtitle: String, val icon: ImageVector, val subtitleColor: Color = LoqtaGreen)
private data class ProductRow(val name: String, val price: String, val status: String)
private data class MessageRow(val name: String, val preview: String, val time: String, val initial: String, val color: Color)

private val stats = listOf(
    StatCard("دخل الشهر", "SAR 8,420", "+12%", Icons.Default.Wallet),
    StatCard("الطلبات", "24", "هذا الأسبوع", Icons.Default.ShoppingCart),
    StatCard("المشاهدات", "1,240", "+8%", Icons.Default.RemoveRedEye),
    StatCard("معدل التحويل", "3.2%", "هذا الشهر", Icons.Default.Speed, Color(0xFF5C6BC0)),
)

private val recentProducts = listOf(
    ProductRow("حذاء نايك الرياضي", "350 ج.م", "تم التحديث منذ ساعة"),
    ProductRow("ساعة ذكية", "1,200 ج.م", "تم التحديث منذ 3 ساعات"),
)

private val messages = listOf(
    MessageRow("أحمد محمد", "هل المنتج متاح؟", "10:30 م", "أ", Color(0xFF5C6BC0)),
    MessageRow("سارة علي", "ما آخر سعر؟", "أمس", "س", Color(0xFF26A69A)),
    MessageRow("محمد خالد", "متى يمكن الاستلام؟", "أمس", "م", Color(0xFFEF5350)),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerDashboardScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    onAddProduct: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            LoqtaTopBar(onSearchClick = { onNavigate("search") })
        },
        bottomBar = {
            LoqtaBottomBar(
                currentRoute = currentRoute,
                onItemClick = { onNavigate(it.route) },
            )
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            item {
                Column {
                    Text("لوحة التحكم", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    Text(
                        "مرحباً بك! إليك ملخص نشاطك",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            item {
                Button(
                    onClick = onAddProduct,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LoqtaGreen),
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("أضف منتج جديد", fontWeight = FontWeight.Bold)
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    stats.take(2).forEach { stat ->
                        StatCardView(stat, Modifier.weight(1f))
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    stats.drop(2).forEach { stat ->
                        StatCardView(stat, Modifier.weight(1f))
                    }
                }
            }

            item { SalesChartCard() }
            item { RecentProductsSection(onViewAll = { onNavigate("my_products") }) }
            item { MessagesSection(onViewAll = { onNavigate("conversations") }) }
        }
    }
}

@Composable
private fun StatCardView(stat: StatCard, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Icon(stat.icon, contentDescription = null, tint = LoqtaGreenDark, modifier = Modifier.size(20.dp))
                Text(stat.label, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(stat.value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(stat.subtitle, style = MaterialTheme.typography.bodySmall, color = stat.subtitleColor)
        }
    }
}

@Composable
private fun SalesChartCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("نشاط المبيعات", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                LegendDot("الزيارات", Color(0xFF3949AB))
                LegendDot("الطلبات", LoqtaGreen)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom,
            ) {
                val days = listOf("جمعة", "خميس", "أربعاء", "ثلاثاء", "اثنين", "أحد", "سبت")
                days.forEachIndexed { index, day ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(horizontalArrangement = Arrangement.spacedBy(2.dp), verticalAlignment = Alignment.Bottom) {
                            Box(
                                modifier = Modifier
                                    .width(8.dp)
                                    .height((30 + index * 8).dp)
                                    .background(Color(0xFF3949AB), RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp)),
                            )
                            Box(
                                modifier = Modifier
                                    .width(8.dp)
                                    .height((20 + index * 5).dp)
                                    .background(LoqtaGreen, RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp)),
                            )
                        }
                        Text(day, style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(top = 4.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun LegendDot(label: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(color))
        Spacer(modifier = Modifier.width(4.dp))
        Text(label, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
private fun RecentProductsSection(onViewAll: () -> Unit) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextButton(onClick = onViewAll) {
                Text("مشاهدة الكل", color = LoqtaGreen)
            }
            Text("آخر المنتجات المضافة", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }
        recentProducts.forEach { product ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFECEFF1)),
                    )
                    Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                        Text(product.name, fontWeight = FontWeight.Bold)
                        Text(product.status, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(product.price, color = LoqtaGreen, fontWeight = FontWeight.Bold)
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Delete, contentDescription = "حذف", tint = Color(0xFFE53935))
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Edit, contentDescription = "تعديل")
                    }
                }
            }
        }
    }
}

@Composable
private fun MessagesSection(onViewAll: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .background(LoqtaGreen.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                ) {
                    Text("3 جديد", color = LoqtaGreen, style = MaterialTheme.typography.labelSmall)
                }
                Text("آخر الرسائل", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(12.dp))
            messages.forEach { message ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(message.color),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(message.initial, color = Color.White, fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                        Text(message.name, fontWeight = FontWeight.Bold)
                        Text(message.preview, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1)
                    }
                    Text(message.time, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            TextButton(onClick = onViewAll, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("الانتقال للرسائل", color = LoqtaGreen)
            }
        }
    }
}
