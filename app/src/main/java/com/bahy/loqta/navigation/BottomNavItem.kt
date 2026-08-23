package com.bahy.loqta.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    HOME(
        route = Routes.HOME,
        label = "الرئيسية",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    ),
    CATEGORIES(
        route = Routes.CATEGORIES,
        label = "الأقسام",
        selectedIcon = Icons.Filled.Category,
        unselectedIcon = Icons.Outlined.Category,
    ),
    SELL(
        route = Routes.ADD_PRODUCT_IMAGES,
        label = "أضف",
        selectedIcon = Icons.Filled.Add,
        unselectedIcon = Icons.Outlined.Add,
    ),
    FAVORITES(
        route = Routes.WISHLIST,
        label = "المفضلة",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
    ),
    PROFILE(
        route = Routes.MY_PROFILE,
        label = "حسابي",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
    ),
}

val bottomNavRoutes = setOf(
    Routes.HOME,
    Routes.CATEGORIES,
    Routes.WISHLIST,
    Routes.MY_PROFILE,
)
