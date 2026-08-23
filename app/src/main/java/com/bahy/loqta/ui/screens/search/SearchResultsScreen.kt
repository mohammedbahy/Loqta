package com.bahy.loqta.ui.screens.search

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
import com.bahy.loqta.ui.components.SearchBar
import com.bahy.loqta.ui.components.SortBottomSheet
import com.bahy.loqta.ui.theme.LoqtaGreen
import com.bahy.loqta.ui.theme.LoqtaGreenDark

private val searchResults = listOf(
    ProductCardData("1", "سماعات بلوتوث عازلة", "450", "القاهرة", "منذ ساعتين", Color(0xFFECEFF1), true),
    ProductCardData("2", "iPhone 15 Pro", "28,000", "الرياض", "منذ 5 ساعات", Color(0xFFE3F2FD)),
    ProductCardData("3", "طاولة طعام خشبية", "1,200", "جدة", "منذ يوم", Color(0xFFFFF3E0)),
    ProductCardData("4", "حذاء نايك رياضي", "350", "القاهرة", "منذ يومين", Color(0xFFFFCDD2), true),
    ProductCardData("5", "لابتوب Dell XPS", "8,500", "الدمام", "منذ 3 أيام", Color(0xFFE8EAF6)),
    ProductCardData("6", "ساعة Apple Watch", "1,800", "الرياض", "منذ أسبوع", Color(0xFFE0F2F1)),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultsScreen(
    initialQuery: String = "",
    onBack: () -> Unit = {},
    onProductClick: (String) -> Unit = {},
) {
    var query by remember { mutableStateOf(initialQuery.ifEmpty { "سماعات بلوتوث" }) }
    var showFilterSheet by remember { mutableStateOf(false) }
    var showSortSheet by remember { mutableStateOf(false) }
    var filters by remember { mutableStateOf(ProductFilterState()) }
    var sortOption by remember { mutableStateOf(ProductSortOption.NEWEST) }

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
                title = { Text("نتائج البحث", fontWeight = FontWeight.Bold, color = LoqtaGreenDark) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "رجوع")
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
            SearchBar(
                query = query,
                onQueryChange = { query = it },
                placeholder = "ابحث عن لقطة...",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                leadingIcon = {
                    IconButton(onClick = { showFilterSheet = true }) {
                        Icon(Icons.Default.Tune, contentDescription = "تصفية")
                    }
                },
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "${searchResults.size} نتيجة لـ \"$query\"",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Row {
                    TextButton(onClick = { showFilterSheet = true }) {
                        Icon(Icons.Default.Tune, contentDescription = null, modifier = Modifier.padding(end = 4.dp), tint = LoqtaGreen)
                        Text("تصفية", color = LoqtaGreen)
                    }
                    TextButton(onClick = { showSortSheet = true }) {
                        Icon(Icons.AutoMirrored.Filled.Sort, contentDescription = null, modifier = Modifier.padding(end = 4.dp), tint = LoqtaGreen)
                        Text(sortOption.label, color = LoqtaGreen)
                    }
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(searchResults) { product ->
                    ProductGridCard(
                        product = product,
                        onClick = { onProductClick(product.id) },
                    )
                }
            }
        }
    }
}
