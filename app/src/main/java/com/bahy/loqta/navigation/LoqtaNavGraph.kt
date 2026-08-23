package com.bahy.loqta.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bahy.loqta.ui.screens.auth.CompleteProfileScreen
import com.bahy.loqta.ui.screens.auth.ForgotPasswordScreen
import com.bahy.loqta.ui.screens.auth.LoginScreen
import com.bahy.loqta.ui.screens.auth.OnboardingScreen
import com.bahy.loqta.ui.screens.auth.OtpVerificationScreen
import com.bahy.loqta.ui.screens.auth.RegisterScreen
import com.bahy.loqta.ui.screens.auth.ResetPasswordScreen
import com.bahy.loqta.ui.screens.auth.SplashScreen
import com.bahy.loqta.ui.screens.categories.CategoriesScreen
import com.bahy.loqta.ui.screens.categories.CategoryProductsScreen
import com.bahy.loqta.ui.screens.product.ProductDetailsScreen
import com.bahy.loqta.ui.screens.product.ProductGalleryScreen
import com.bahy.loqta.ui.screens.chat.ChatDetailsScreen
import com.bahy.loqta.ui.screens.chat.ConversationsScreen
import com.bahy.loqta.ui.screens.common.PlaceholderScreen
import com.bahy.loqta.ui.screens.help.HelpCenterScreen
import com.bahy.loqta.ui.screens.home.HomeScreen
import com.bahy.loqta.ui.screens.notifications.NotificationsScreen
import com.bahy.loqta.ui.screens.profile.EditProfileScreen
import com.bahy.loqta.ui.screens.profile.MyProfileScreen
import com.bahy.loqta.ui.screens.profile.SettingsScreen
import com.bahy.loqta.ui.screens.search.SearchResultsScreen
import com.bahy.loqta.ui.screens.search.SearchScreen
import com.bahy.loqta.ui.screens.seller.AddProductImagesScreen
import com.bahy.loqta.ui.screens.seller.AddProductInfoScreen
import com.bahy.loqta.ui.screens.seller.AddProductPriceScreen
import com.bahy.loqta.ui.screens.seller.EditProductScreen
import com.bahy.loqta.ui.screens.seller.MyProductsScreen
import com.bahy.loqta.ui.screens.seller.PreviewProductScreen
import com.bahy.loqta.ui.screens.seller.PublishSuccessScreen
import com.bahy.loqta.ui.screens.seller.SellerDashboardScreen
import com.bahy.loqta.ui.screens.wishlist.WishlistScreen

@Composable
fun LoqtaApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Routes.SPLASH

    LoqtaNavGraph(navController = navController, currentRoute = currentRoute)
}

@Composable
fun LoqtaNavGraph(
    navController: NavHostController,
    currentRoute: String,
) {
    val navigate: (String) -> Unit = { route ->
        navController.navigate(route) {
            if (route in bottomNavRoutes) {
                popUpTo(Routes.HOME) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    val navigateAndClear: (String) -> Unit = { route ->
        navController.navigate(route) {
            popUpTo(0) { inclusive = true }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        modifier = Modifier.fillMaxSize(),
    ) {
        // ── Authentication ──
        composable(Routes.SPLASH) {
            SplashScreen(onNavigateToOnboarding = { navigate(Routes.onboarding(0)) })
        }

        composable(
            route = Routes.ONBOARDING,
            arguments = listOf(navArgument("page") { type = NavType.IntType }),
        ) { backStackEntry ->
            val page = backStackEntry.arguments?.getInt("page") ?: 0
            OnboardingScreen(
                page = page,
                onNext = {
                    if (page < 2) navigate(Routes.onboarding(page + 1))
                    else navigateAndClear(Routes.LOGIN)
                },
                onSkip = { navigateAndClear(Routes.LOGIN) },
                onBack = {
                    if (page > 0) navController.popBackStack()
                },
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                onLogin = { navigateAndClear(Routes.HOME) },
                onRegister = { navigate(Routes.REGISTER) },
                onForgotPassword = { navigate(Routes.FORGOT_PASSWORD) },
                onGuestLogin = { navigateAndClear(Routes.HOME) },
            )
        }

        composable(Routes.REGISTER) {
            RegisterScreen(
                onRegister = { navigate(Routes.COMPLETE_PROFILE) },
                onLogin = { navController.popBackStack() },
            )
        }

        composable(Routes.FORGOT_PASSWORD) {
            ForgotPasswordScreen(
                onBack = { navController.popBackStack() },
                onSendCode = { navigate(Routes.OTP_VERIFICATION) },
                onLogin = { navController.popBackStack() },
            )
        }

        composable(Routes.OTP_VERIFICATION) {
            OtpVerificationScreen(
                onBack = { navController.popBackStack() },
                onVerify = { navigate(Routes.RESET_PASSWORD) },
            )
        }

        composable(Routes.RESET_PASSWORD) {
            ResetPasswordScreen(
                onBack = { navController.popBackStack() },
                onUpdate = { navigateAndClear(Routes.LOGIN) },
            )
        }

        composable(Routes.COMPLETE_PROFILE) {
            CompleteProfileScreen(
                onComplete = { navigateAndClear(Routes.HOME) },
                onSkip = { navigateAndClear(Routes.HOME) },
            )
        }

        // ── Main User Flow ──
        composable(Routes.HOME) {
            HomeScreen(
                currentRoute = currentRoute,
                onNavigate = navigate,
            )
        }

        composable(Routes.SEARCH) {
            SearchScreen(
                currentRoute = currentRoute,
                onNavigate = navigate,
                onSearch = { navigate(Routes.SEARCH_RESULTS) },
            )
        }

        composable(Routes.SEARCH_RESULTS) {
            SearchResultsScreen(
                onBack = { navController.popBackStack() },
                onProductClick = { navigate(Routes.productDetails(it)) },
            )
        }

        composable(Routes.CATEGORIES) {
            CategoriesScreen(
                currentRoute = currentRoute,
                onNavigate = navigate,
                onCategoryClick = { navigate(Routes.categoryProducts(it)) },
            )
        }

        composable(
            route = Routes.CATEGORY_PRODUCTS,
            arguments = listOf(navArgument("categoryId") { type = NavType.StringType }),
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
            CategoryProductsScreen(
                categoryId = categoryId,
                onBack = { navController.popBackStack() },
                onProductClick = { navigate(Routes.productDetails(it)) },
            )
        }

        composable(
            route = Routes.PRODUCT_DETAILS,
            arguments = listOf(navArgument("productId") { type = NavType.StringType }),
        ) {
            ProductDetailsScreen(
                onBack = { navController.popBackStack() },
                onOpenGallery = { navigate(Routes.productGallery("1")) },
                onChat = { navigate(Routes.chatDetails("1")) },
                onMakeOffer = { navigate(Routes.makeOffer("1")) },
            )
        }

        composable(
            route = Routes.PRODUCT_GALLERY,
            arguments = listOf(navArgument("productId") { type = NavType.StringType }),
        ) {
            ProductGalleryScreen(onBack = { navController.popBackStack() })
        }
        composable(Routes.WISHLIST) {
            WishlistScreen(
                currentRoute = currentRoute,
                onNavigate = navigate,
                onProductClick = { navigate(Routes.productDetails(it)) },
            )
        }

        composable(Routes.NOTIFICATIONS) {
            NotificationsScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.CONVERSATIONS) {
            ConversationsScreen(
                onNavigate = navigate,
                onChatClick = { navigate(Routes.chatDetails(it)) },
            )
        }

        composable(
            route = Routes.CHAT_DETAILS,
            arguments = listOf(navArgument("chatId") { type = NavType.StringType }),
        ) {
            ChatDetailsScreen(
                onBack = { navController.popBackStack() },
                onViewProduct = { navigate(Routes.productDetails("1")) },
            )
        }

        composable(Routes.MY_PROFILE) {
            MyProfileScreen(
                currentRoute = currentRoute,
                onNavigate = navigate,
                onEditProfile = { navigate(Routes.EDIT_PROFILE) },
            )
        }

        composable(Routes.EDIT_PROFILE) {
            EditProfileScreen(
                onBack = { navController.popBackStack() },
                onSave = { navController.popBackStack() },
            )
        }
        composable(Routes.SETTINGS) {
            SettingsScreen(onBack = { navController.popBackStack() })
        }
        placeholder(Routes.MY_REVIEWS, "تقييماتي", navController)
        composable(Routes.HELP_CENTER) {
            HelpCenterScreen(onBack = { navController.popBackStack() })
        }
        placeholder(Routes.ABOUT_APP, "عن التطبيق", navController)

        // ── Seller Flow ──
        composable(Routes.SELLER_DASHBOARD) {
            SellerDashboardScreen(
                currentRoute = currentRoute,
                onNavigate = navigate,
                onAddProduct = { navigate(Routes.ADD_PRODUCT_IMAGES) },
            )
        }

        composable(Routes.MY_PRODUCTS) {
            MyProductsScreen(
                onBack = { navController.popBackStack() },
                onAddProduct = { navigate(Routes.ADD_PRODUCT_IMAGES) },
                onEditProduct = { navigate(Routes.editProduct(it)) },
                onPreviewProduct = { navigate(Routes.PREVIEW_PRODUCT) },
            )
        }

        composable(Routes.ADD_PRODUCT_IMAGES) {
            AddProductImagesScreen(
                onClose = { navController.popBackStack() },
                onNext = { navigate(Routes.ADD_PRODUCT_INFO) },
            )
        }

        composable(Routes.ADD_PRODUCT_INFO) {
            AddProductInfoScreen(
                onClose = { navController.popBackStack() },
                onNext = { navigate(Routes.ADD_PRODUCT_PRICE) },
            )
        }

        composable(Routes.ADD_PRODUCT_PRICE) {
            AddProductPriceScreen(
                onBack = { navController.popBackStack() },
                onPublish = { navigate(Routes.PREVIEW_PRODUCT) },
            )
        }

        composable(Routes.PREVIEW_PRODUCT) {
            PreviewProductScreen(
                onBack = { navController.popBackStack() },
                onEdit = { navController.popBackStack() },
                onPublish = { navigate(Routes.PUBLISH_SUCCESS) },
            )
        }

        composable(Routes.PUBLISH_SUCCESS) {
            PublishSuccessScreen(
                onViewListing = { navigate(Routes.MY_PRODUCTS) },
                onBackToHome = { navigateAndClear(Routes.HOME) },
            )
        }
        composable(
            route = Routes.EDIT_PRODUCT,
            arguments = listOf(navArgument("productId") { type = NavType.StringType }),
        ) {
            EditProductScreen(
                onBack = { navController.popBackStack() },
                onSave = { navController.popBackStack() },
            )
        }
        sellerPlaceholder(Routes.SELLER_ANALYTICS, "تحليلات البائع", navController)
        sellerPlaceholder(Routes.SELLER_PROFILE, "ملف البائع", navController)
        sellerPlaceholder(Routes.SELLER_REVIEWS, "تقييمات البائع", navController)

        // ── Buyer Actions ──
        placeholder(Routes.MAKE_OFFER, "تقديم عرض", navController, "productId")
        placeholder(Routes.OFFER_HISTORY, "سجل العروض", navController)
        placeholder(Routes.PURCHASE_HISTORY, "سجل المشتريات", navController)
        placeholder(Routes.ORDER_DETAILS, "تفاصيل الطلب", navController, "orderId")

        // ── Admin Flow ──
        adminPlaceholder(Routes.ADMIN_LOGIN, "دخول المشرف", navController)
        adminPlaceholder(Routes.ADMIN_DASHBOARD, "لوحة المشرف", navController)
        adminPlaceholder(Routes.USER_MANAGEMENT, "إدارة المستخدمين", navController)
        adminPlaceholder(Routes.SELLER_MANAGEMENT, "إدارة البائعين", navController)
        adminPlaceholder(Routes.SELLER_VERIFICATION, "توثيق البائعين", navController)
        adminPlaceholder(Routes.PRODUCT_MODERATION, "مراجعة المنتجات", navController)
        adminPlaceholder(Routes.REPORTS_MANAGEMENT, "إدارة البلاغات", navController)
        adminPlaceholder(Routes.CATEGORIES_MANAGEMENT, "إدارة الأقسام", navController)
        adminPlaceholder(Routes.ADMIN_ANALYTICS, "تحليلات المشرف", navController)
        adminPlaceholder(Routes.APP_SETTINGS, "إعدادات التطبيق", navController)

        // ── Common ──
        composable(Routes.NO_INTERNET) {
            PlaceholderScreen(title = "لا يوجد اتصال بالإنترنت")
        }
        composable(Routes.LOADING) {
            PlaceholderScreen(title = "جاري التحميل...")
        }
        composable(Routes.EMPTY_STATE) {
            PlaceholderScreen(title = "لا توجد بيانات")
        }
        composable(Routes.ERROR) {
            PlaceholderScreen(title = "حدث خطأ")
        }
        composable(Routes.MAINTENANCE) {
            PlaceholderScreen(title = "صيانة التطبيق")
        }
        composable(Routes.NOT_FOUND) {
            PlaceholderScreen(title = "الصفحة غير موجودة")
        }
    }
}

private fun androidx.navigation.NavGraphBuilder.authPlaceholder(
    route: String,
    title: String,
    navController: NavHostController,
) {
    composable(route) {
        PlaceholderScreen(title = title, onBack = { navController.popBackStack() })
    }
}

private fun androidx.navigation.NavGraphBuilder.sellerPlaceholder(
    route: String,
    title: String,
    navController: NavHostController,
) {
    composable(route) {
        PlaceholderScreen(title = title, onBack = { navController.popBackStack() })
    }
}

private fun androidx.navigation.NavGraphBuilder.adminPlaceholder(
    route: String,
    title: String,
    navController: NavHostController,
) {
    composable(route) {
        PlaceholderScreen(title = title, onBack = { navController.popBackStack() })
    }
}

private fun androidx.navigation.NavGraphBuilder.placeholder(
    route: String,
    title: String,
    navController: NavHostController,
    argName: String? = null,
) {
    if (argName != null) {
        composable(
            route = route,
            arguments = listOf(navArgument(argName) { type = NavType.StringType }),
        ) {
            PlaceholderScreen(title = title, onBack = { navController.popBackStack() })
        }
    } else {
        composable(route) {
            val showBack = route !in bottomNavRoutes
            PlaceholderScreen(
                title = title,
                onBack = if (showBack) {{ navController.popBackStack() }} else null,
            )
        }
    }
}
