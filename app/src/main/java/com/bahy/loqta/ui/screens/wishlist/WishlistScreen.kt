package com.bahy.loqta.ui.screens.wishlist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import com.bahy.loqta.ui.components.LoqtaBottomBar
import com.bahy.loqta.ui.components.LoqtaTopBar
import com.bahy.loqta.ui.theme.LoqtaBadgeRed
import com.bahy.loqta.ui.theme.LoqtaGreen
import com.bahy.loqta.ui.theme.LoqtaGreenDark

private data class WishlistProduct(val id: String, val name: String, val price: String, val color: Color, val hasBadge: Boolean = false)

private val filters = listOf("الكل", "إلكترونيات", "أثاث", "ملابس")
private val products = listOf(
    WishlistProduct("2", "ساعة يد ذكية", "890", Color(0xFFD7CCC8)),
    WishlistProduct("1", "سماعات بلوتوث عازلة", "450", Color(0xFFCFD8DC), hasBadge = true),
    WishlistProduct("5", "كاميرا فورية", "275", Color(0xFFB3E5FC)),
    WishlistProduct("4", "حذاء رياضي", "320", Color(0xFFFFCDD2)),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    onProductClick: (String) -> Unit = {},
) {
    var selectedFilter by remember { mutableIntStateOf(0) }

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
                .padding(horizontal = 16.dp),
        ) {
            Text("المفضلة", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = LoqtaGreenDark)
            Text(
                "المنتجات التي نالت إعجابك في مكان واحد.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                itemsIndexed(filters) { index, filter ->
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

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(products) { product ->
                    WishlistProductCard(product, onClick = { onProductClick(product.id) })
                }
            }
        }
    }
}

@Composable
private fun WishlistProductCard(product: WishlistProduct, onClick: () -> Unit = {}) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier.border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f), RoundedCornerShape(16.dp)),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(product.color),
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                ) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = null, tint = LoqtaGreen)
                }
                if (product.hasBadge) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp)
                            .background(LoqtaBadgeRed, RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp),
                    ) {
                        Text("!Loqta", color = Color.White, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Text(product.name, fontWeight = FontWeight.Bold, maxLines = 1)
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(product.price, color = LoqtaGreen, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                    Text(" ر.س", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
