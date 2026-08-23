package com.bahy.loqta.ui.screens.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bahy.loqta.ui.components.FilterBottomSheet
import com.bahy.loqta.ui.components.ProductCardData
import com.bahy.loqta.ui.components.ProductFilterState
import com.bahy.loqta.ui.components.ProductGridCard
import com.bahy.loqta.ui.components.ProductSortOption
import com.bahy.loqta.ui.components.SortBottomSheet
import com.bahy.loqta.ui.theme.LoqtaGreen
import com.bahy.loqta.ui.theme.LoqtaGreenDark

private val categoryNames = mapOf(
    "electronics" to "إلكترونيات",
    "home" to "المنزل",
    "vehicles" to "مركبات",
    "fashion" to "أزياء",
    "books" to "كتب",
    "sports" to "رياضة",
    "toys" to "ألعاب",
    "others" to "أخرى",
)

private val sortFilters = listOf("الأحدث", "الأقل سعراً", "الأعلى سعراً", "الأقرب")

private fun productsForCategory(categoryId: String) = listOf(
    ProductCardData("1", "iPhone 14 Pro", "22,000", "الرياض", "منذ ساعة", Color(0xFFE3F2FD), true),
    ProductCardData("2", "سماعات AirPods", "650", "جدة", "منذ 3 ساعات", Color(0xFFECEFF1)),
    ProductCardData("3", "لابتوب MacBook", "15,000", "الدمام", "منذ يوم", Color(0xFFE8EAF6)),
    ProductCardData("4", "ساعة ذكية", "890", "الرياض", "منذ يومين", Color(0xFFE0F2F1), true),
    ProductCardData("5", "كاميرا Canon", "3,200", "القاهرة", "منذ 3 أيام", Color(0xFFFFF3E0)),
    ProductCardData("6", "تابلت سامسونج", "1,500", "الرياض", "منذ أسبوع", Color(0xFFF3E5F5)),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryProductsScreen(
    categoryId: String,
    onBack: () -> Unit = {},
    onProductClick: (String) -> Unit = {},
) {
    val categoryName = categoryNames[categoryId] ?: "المنتجات"
    var selectedSort by remember { mutableIntStateOf(0) }
    var showFilterSheet by remember { mutableStateOf(false) }
    var showSortSheet by remember { mutableStateOf(false) }
    var filters by remember { mutableStateOf(ProductFilterState()) }
    var sortOption by remember { mutableStateOf(ProductSortOption.NEWEST) }
    val products = remember(categoryId) { productsForCategory(categoryId) }

    if (showFilterSheet) {
        FilterBottomSheet(
            initialFilters = filters,
            onDismiss = { showFilterSheet = false },
            onApply = { filters = it },
        )
    }

    if (showSortSheet) {
        SortBottomSheet(
            selectedOption = sortOption,
            onDismiss = { showSortSheet = false },
            onApply = { sortOption = it },
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(categoryName, fontWeight = FontWeight.Bold, color = LoqtaGreenDark) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "رجوع")
                    }
                },
                actions = {
                    IconButton(onClick = { showFilterSheet = true }) {
                        Icon(Icons.Default.Tune, contentDescription = "تصفية", tint = LoqtaGreenDark)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "${products.size} منتج",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                TextButton(onClick = { showSortSheet = true }) {
                    Icon(Icons.AutoMirrored.Filled.Sort, contentDescription = null, modifier = Modifier.padding(end = 4.dp), tint = LoqtaGreen)
                    Text(sortOption.label, color = LoqtaGreen)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                sortFilters.forEachIndexed { index, filter ->
                    FilterChip(
                        selected = selectedSort == index,
                        onClick = { selectedSort = index },
                        label = { Text(filter, style = MaterialTheme.typography.labelSmall) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = LoqtaGreenDark,
                            selectedLabelColor = Color.White,
                            containerColor = Color(0xFFE3F2FD),
                        ),
                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(products) { product ->
                    ProductGridCard(
                        product = product,
                        onClick = { onProductClick(product.id) },
                    )
                }
            }
        }
    }
}
