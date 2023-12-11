package com.dscreate_app.vkclient

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.dscreate_app.vkclient.navigation.Screens

sealed class NavigationItem(
    val screen: Screens,
    val titleResId: Int,
    val icon: ImageVector
) {
    data object Home: NavigationItem(
        screen = Screens.NewsFeed,
        titleResId = R.string.navigation_item_main,
        icon = Icons.Outlined.Home
    )

    data object Favourite: NavigationItem(
        screen = Screens.Favourite,
        titleResId = R.string.navigation_item_favourite,
        icon = Icons.Outlined.Favorite
    )

    data object Profile: NavigationItem(
        screen = Screens.Profile,
        titleResId = R.string.navigation_item_profile,
        icon = Icons.Outlined.Person
    )
}