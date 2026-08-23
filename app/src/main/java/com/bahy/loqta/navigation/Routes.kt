package com.bahy.loqta.navigation

object Routes {
    // Authentication
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding/{page}"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val FORGOT_PASSWORD = "forgot_password"
    const val OTP_VERIFICATION = "otp_verification"
    const val RESET_PASSWORD = "reset_password"
    const val COMPLETE_PROFILE = "complete_profile"

    // Main User Flow
    const val HOME = "home"
    const val SEARCH = "search"
    const val SEARCH_RESULTS = "search_results"
    const val CATEGORIES = "categories"
    const val CATEGORY_PRODUCTS = "category_products/{categoryId}"
    const val PRODUCT_DETAILS = "product_details/{productId}"
    const val PRODUCT_GALLERY = "product_gallery/{productId}"
    const val WISHLIST = "wishlist"
    const val NOTIFICATIONS = "notifications"
    const val CONVERSATIONS = "conversations"
    const val CHAT_DETAILS = "chat_details/{chatId}"
    const val MY_PROFILE = "my_profile"
    const val EDIT_PROFILE = "edit_profile"
    const val SETTINGS = "settings"
    const val MY_REVIEWS = "my_reviews"
    const val HELP_CENTER = "help_center"
    const val ABOUT_APP = "about_app"

    // Seller Flow
    const val SELLER_DASHBOARD = "seller_dashboard"
    const val MY_PRODUCTS = "my_products"
    const val ADD_PRODUCT_IMAGES = "add_product_images"
    const val ADD_PRODUCT_INFO = "add_product_info"
    const val ADD_PRODUCT_PRICE = "add_product_price"
    const val PREVIEW_PRODUCT = "preview_product"
    const val PUBLISH_SUCCESS = "publish_success"
    const val EDIT_PRODUCT = "edit_product/{productId}"
    const val SELLER_ANALYTICS = "seller_analytics"
    const val SELLER_PROFILE = "seller_profile"
    const val SELLER_REVIEWS = "seller_reviews"

    // Buyer Actions
    const val MAKE_OFFER = "make_offer/{productId}"
    const val OFFER_HISTORY = "offer_history"
    const val PURCHASE_HISTORY = "purchase_history"
    const val ORDER_DETAILS = "order_details/{orderId}"

    // Admin Flow
    const val ADMIN_LOGIN = "admin_login"
    const val ADMIN_DASHBOARD = "admin_dashboard"
    const val USER_MANAGEMENT = "user_management"
    const val SELLER_MANAGEMENT = "seller_management"
    const val SELLER_VERIFICATION = "seller_verification"
    const val PRODUCT_MODERATION = "product_moderation"
    const val REPORTS_MANAGEMENT = "reports_management"
    const val CATEGORIES_MANAGEMENT = "categories_management"
    const val ADMIN_ANALYTICS = "admin_analytics"
    const val APP_SETTINGS = "app_settings"

    // Common
    const val NO_INTERNET = "no_internet"
    const val LOADING = "loading"
    const val EMPTY_STATE = "empty_state"
    const val ERROR = "error"
    const val MAINTENANCE = "maintenance"
    const val NOT_FOUND = "not_found"

    fun onboarding(page: Int) = "onboarding/$page"
    fun categoryProducts(categoryId: String) = "category_products/$categoryId"
    fun productDetails(productId: String) = "product_details/$productId"
    fun productGallery(productId: String) = "product_gallery/$productId"
    fun chatDetails(chatId: String) = "chat_details/$chatId"
    fun editProduct(productId: String) = "edit_product/$productId"
    fun makeOffer(productId: String) = "make_offer/$productId"
    fun orderDetails(orderId: String) = "order_details/$orderId"
}
