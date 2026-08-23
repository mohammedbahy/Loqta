package com.bahy.loqta.ui.screens.home

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Chair
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.bahy.loqta.ui.theme.LoqtaBadgeRed
import com.bahy.loqta.ui.theme.LoqtaGreen
import com.bahy.loqta.ui.theme.LoqtaGreenDark
import com.bahy.loqta.ui.theme.LoqtaGreenLight

private data class CategoryItem(val name: String, val icon: ImageVector)
private data class ProductItem(val id: String, val name: String, val price: String, val timeAgo: String)

private val categories = listOf(
    CategoryItem("إلكترونيات", Icons.Default.PhoneAndroid),
    CategoryItem("أجهزة منزلية", Icons.Default.Kitchen),
    CategoryItem("أثاث", Icons.Default.Chair),
    CategoryItem("مركبات", Icons.Default.DirectionsCar),
    CategoryItem("أزياء", Icons.Default.ShoppingBag),
)

private val nearbyProducts = listOf(
    ProductItem("1", "سماعات بلوتوث عازلة", "450 ج.م", "منذ ساعتين"),
    ProductItem("2", "ساعة ذكية", "1,200 ج.م", "منذ 5 ساعات"),
    ProductItem("3", "كاميرا رقمية", "3,500 ج.م", "منذ يوم"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    onMenuClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            LoqtaTopBar(
                onMenuClick = onMenuClick,
                onSearchClick = { onNavigate("search") },
                onNotificationClick = { onNavigate("notifications") },
            )
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
            item { HeroBanner(onAddClick = { onNavigate("add_product_images") }) }
            item { CategoriesSection(onViewAll = { onNavigate("categories") }) }
            item { NearbyProductsSection(onProductClick = { onNavigate("product_details/$it") }) }
            item { PopularSection() }
        }
    }
}

@Composable
private fun HeroBanner(onAddClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(LoqtaGreen, LoqtaGreenLight),
                    ),
                )
                .padding(24.dp),
        ) {
            Column {
                Text(
                    text = "بيع أشياءك المستعملة بكل سهولة",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "حوّل أغراضك القديمة إلى نقود بضغطة زر واحدة في أكبر سوق محلي",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.9f),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onAddClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = LoqtaGreenDark,
                    ),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Text("أضف إعلانك الآن", fontWeight = FontWeight.Bold)
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
            }
        }
    }
}

@Composable
private fun CategoriesSection(onViewAll: () -> Unit) {
    Column {
        SectionHeader(title = "التصنيفات", actionText = "مشاهدة الكل", onAction = onViewAll)
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(categories) { category ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        modifier = Modifier.size(72.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFE3F2FD),
                        ),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(category.icon, contentDescription = category.name, tint = LoqtaGreenDark)
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = category.name, style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Composable
private fun NearbyProductsSection(onProductClick: (String) -> Unit = {}) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = LoqtaGreen, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("القاهرة، مصر", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Text("منتجات قريبة منك", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(nearbyProducts) { product ->
                ProductCard(product, onClick = { onProductClick(product.id) })
            }
        }
    }
}

@Composable
private fun ProductCard(product: ProductItem, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color(0xFFECEFF1)),
            ) {
                Icon(
                    Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(4.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(LoqtaBadgeRed, RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                ) {
                    Text("لقطة", color = Color.White, style = MaterialTheme.typography.labelSmall)
                }
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Text(product.name, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, maxLines = 1)
                Text(product.price, color = LoqtaGreen, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AccessTime, contentDescription = null, modifier = Modifier.size(12.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(product.timeAgo, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

@Composable
private fun PopularSection() {
    Column {
        Text("الأكثر شعبية", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        PopularBanner(
            title = "عروض حصرية",
            subtitle = "أحذية رياضية أصلية",
            description = "وفّر حتى 40% على ماركات مختارة",
            gradientColors = listOf(LoqtaGreenDark, LoqtaGreen),
        )
        Spacer(modifier = Modifier.height(12.dp))
        PopularBanner(
            title = "كاميرات كلاسيك",
            subtitle = "لعشاق التصوير",
            description = "اكتشف مجموعة الكاميرات القديمة",
            gradientColors = listOf(Color(0xFF37474F), Color(0xFF546E7A)),
        )
    }
}

@Composable
private fun PopularBanner(
    title: String,
    subtitle: String,
    description: String,
    gradientColors: List<Color>,
) {
    Card(
        modifier = Modifier.fillMaxWidth().height(140.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.linearGradient(gradientColors))
                .padding(20.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            Column {
                Text(title, color = LoqtaGreenLight, style = MaterialTheme.typography.labelLarge)
                Text(subtitle, color = Color.White, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text(description, color = Color.White.copy(alpha = 0.8f), style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String, actionText: String, onAction: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(onClick = onAction) {
            Text(actionText, color = LoqtaGreen)
        }
        Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
    }
}
