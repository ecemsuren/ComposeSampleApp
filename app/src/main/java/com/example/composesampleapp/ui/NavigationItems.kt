package com.example.composesampleapp.ui

import com.example.composesampleapp.R

sealed class BottomNavigationItems(
    val route: String,
    val name: String,
    val icon: Int
) {
    object Books : BottomNavigationItems("books", "Kitaplar", R.drawable.ic_baseline_menu_book_24)
    object News : BottomNavigationItems("news", "Haberler", R.drawable.ic_baseline_library_books_24)
}

val navItems = listOf(
    BottomNavigationItems.Books,
    BottomNavigationItems.News
)