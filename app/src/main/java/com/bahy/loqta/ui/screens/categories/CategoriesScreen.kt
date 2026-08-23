package com.bahy.loqta.ui.screens.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Chair
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bahy.loqta.ui.components.LoqtaBottomBar
import com.bahy.loqta.ui.components.LoqtaTopBar
import com.bahy.loqta.ui.components.SearchBar
import com.bahy.loqta.ui.theme.LoqtaBadgeRed
import com.bahy.loqta.ui.theme.LoqtaGreen
import com.bahy.loqta.ui.theme.LoqtaGreenDark
import com.bahy.loqta.ui.theme.LoqtaGreenLight

private data class CategoryGridItem(
    val id: String,
    val name: String,
    val icon: ImageVector,
    val isComingSoon: Boolean = false,
)

private val gridCategories = listOf(
    CategoryGridItem("electronics", "إلكترونيات", Icons.Default.Computer),
    CategoryGridItem("home", "المنزل", Icons.Default.Home),
    CategoryGridItem("vehicles", "مركبات", Icons.Default.DirectionsCar),
    CategoryGridItem("fashion", "أزياء", Icons.Default.ShoppingBag),
    CategoryGridItem("books", "كتب", Icons.Default.Book),
    CategoryGridItem("sports", "رياضة", Icons.Default.FitnessCenter),
    CategoryGridItem("toys", "ألعاب", Icons.Default.SmartToy),
    CategoryGridItem("others", "أخرى", Icons.Default.Category),
    CategoryGridItem("soon", "قريباً", Icons.Default.MoreHoriz, isComingSoon = true),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    onCategoryClick: (String) -> Unit = {},
) {
    var query by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            LoqtaTopBar(
                title = "الأقسام",
                showLogo = false,
            )
        },
        bottomBar = {
            LoqtaBottomBar(
                currentRoute = currentRoute,
                onItemClick = { onNavigate(it.route) },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
        ) {
            SearchBar(
                query = query,
                onQueryChange = { query = it },
                placeholder = "ابحث في الأقسام...",
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(gridCategories) { category ->
                    CategoryGridCard(
                        category = category,
                        onClick = { if (!category.isComingSoon) onCategoryClick(category.id) },
                    )
                }
            }

            Text(
                text = "أقسام مميزة",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp),
            )

            FeaturedBanner()
        }
    }
}

@Composable
private fun CategoryGridCard(
    category: CategoryGridItem,
    onClick: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            onClick = onClick,
            enabled = !category.isComingSoon,
            modifier = Modifier.size(90.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (category.isComingSoon) {
                    Color.Transparent
                } else {
                    LoqtaGreen.copy(alpha = 0.15f)
                },
            ),
            border = if (category.isComingSoon) {
                androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            } else {
                null
            },
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Icon(category.icon, contentDescription = category.name, tint = LoqtaGreenDark, modifier = Modifier.size(32.dp))
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(category.name, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
private fun FeaturedBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.linearGradient(listOf(Color(0xFF37474F), Color(0xFF607D8B)))),
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                horizontalAlignment = Alignment.End,
            ) {
                Box(
                    modifier = Modifier
                        .background(LoqtaBadgeRed, RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                ) {
                    Text("LOQTA BADGE", color = Color.White, style = MaterialTheme.typography.labelSmall)
                }
                Text(
                    "عروض الصيف المميزة",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    "اكتشف خصومات تصل إلى 40%",
                    color = LoqtaGreenLight,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}
