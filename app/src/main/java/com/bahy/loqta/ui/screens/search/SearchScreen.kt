package com.bahy.loqta.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bahy.loqta.ui.components.FilterBottomSheet
import com.bahy.loqta.ui.components.LoqtaBottomBar
import com.bahy.loqta.ui.components.LoqtaTopBar
import com.bahy.loqta.ui.components.ProductFilterState
import com.bahy.loqta.ui.components.SearchBar
import com.bahy.loqta.ui.theme.LoqtaGreen
import com.bahy.loqta.ui.theme.LoqtaGreenDark

private data class PopularCategory(val name: String, val icon: ImageVector, val color: Color)
private data class ExploreCategory(val name: String, val description: String, val color: Color, val fullWidth: Boolean = false)

private val recentSearches = listOf("iPhone 15 Pro", "Dining Table", "Nike Shoes")
private val popularCategories = listOf(
    PopularCategory("موبايلات", Icons.Default.PhoneAndroid, Color(0xFFB2DFDB)),
    PopularCategory("سيارات", Icons.Default.DirectionsCar, Color(0xFFBBDEFB)),
    PopularCategory("أثاث", Icons.Default.Home, Color(0xFFF8BBD0)),
    PopularCategory("لابتوب", Icons.Default.Computer, Color(0xFFC8E6C9)),
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SearchScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    onSearch: () -> Unit = {},
) {
    var query by remember { mutableStateOf("") }
    var showFilterSheet by remember { mutableStateOf(false) }
    var filters by remember { mutableStateOf(ProductFilterState()) }

    if (showFilterSheet) {
        FilterBottomSheet(
            initialFilters = filters,
            onDismiss = { showFilterSheet = false },
            onApply = { filters = it },
        )
    }

    Scaffold(
        topBar = {
            LoqtaTopBar(
                showLogo = true,
                onSearchClick = {},
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
            item {
                SearchBar(
                    query = query,
                    onQueryChange = { query = it },
                    placeholder = "ابحث عن لقطة...",
                    leadingIcon = {
                        IconButton(onClick = { showFilterSheet = true }) {
                            Icon(Icons.Default.Tune, contentDescription = "تصفية")
                        }
                    },
                    trailingIcon = {
                        IconButton(onClick = onSearch) {
                            Icon(
                                painter = androidx.compose.ui.res.painterResource(com.bahy.loqta.R.drawable.search),
                                contentDescription = "بحث",
                            )
                        }
                    },
                )
            }

            item { RecentSearchesSection(onSearch = onSearch) }
            item { PopularCategoriesSection() }
            item { ExploreCategoriesSection() }
            item { FilterTipBanner() }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun RecentSearchesSection(onSearch: () -> Unit = {}) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextButton(onClick = {}) {
                Text("مسح الكل", color = LoqtaGreen)
            }
            Text("عمليات البحث الأخيرة", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            recentSearches.forEach { search ->
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color(0xFFE3F2FD),
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(Icons.Default.Close, contentDescription = null, modifier = Modifier.size(16.dp))
                        Text(search, modifier = Modifier.padding(horizontal = 8.dp).clickable { onSearch() }, style = MaterialTheme.typography.bodyMedium)
                        Icon(Icons.Default.History, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun PopularCategoriesSection() {
    Column {
        Text("الفئات الشائعة", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(popularCategories) { category ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(category.color),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(category.icon, contentDescription = category.name, tint = LoqtaGreenDark)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(category.name, style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Composable
private fun ExploreCategoriesSection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextButton(onClick = {}) {
                Text("عرض المزيد", color = LoqtaGreen)
            }
            Text("استكشف الفئات", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth().height(120.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF263238)),
        ) {
            Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.BottomEnd) {
                Column(horizontalAlignment = Alignment.End) {
                    Text("الإلكترونيات", color = Color.White, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
                    Text("هواتف، لابتوب، وأكثر", color = Color.White.copy(alpha = 0.7f), style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            ExploreCard("المنزل والحديقة", Color(0xFFF8BBD0), Modifier.weight(1f))
            ExploreCard("أزياء وملابس", Color(0xFFC8E6C9), Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            ExploreCard("هوايات ورياضة", Color(0xFFBBDEFB), Modifier.weight(1f), icon = Icons.Default.SportsEsports)
            ExploreCard("عقارات", Color(0xFFD7CCC8), Modifier.weight(1f))
        }
    }
}

@Composable
private fun ExploreCard(
    title: String,
    color: Color,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
) {
    Card(
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color),
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(12.dp)) {
            if (icon != null) {
                Icon(icon, contentDescription = null, modifier = Modifier.align(Alignment.Center), tint = LoqtaGreenDark)
            }
            Text(
                text = title,
                modifier = Modifier.align(Alignment.BottomEnd),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@Composable
private fun FilterTipBanner() {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(LoqtaGreenDark),
                contentAlignment = Alignment.Center,
            ) {
                Icon(Icons.Default.Lightbulb, contentDescription = null, tint = Color.White)
            }
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = "هل تبحث عن شيء محدد؟ جرب استخدام عوامل التصفية لتحديد السعر والموقع بدقة!",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}
