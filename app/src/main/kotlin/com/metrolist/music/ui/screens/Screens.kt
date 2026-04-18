/**
 * Metrolist Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.metrolist.music.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.metrolist.music.R

@Immutable
sealed class Screens(
    @StringRes val titleId: Int,
    @DrawableRes val iconIdInactive: Int,
    @DrawableRes val iconIdActive: Int,
    val route: String,
    val drawerSection: DrawerSection? = null
) {

    enum class DrawerSection {
        PRIMARY,
        SECONDARY,
        SETTINGS
    }

    object Home : Screens(
        titleId = R.string.home,
        iconIdInactive = R.drawable.home_outlined,
        iconIdActive = R.drawable.home_filled,
        route = "home",
        drawerSection = DrawerSection.PRIMARY
    )

    object Search : Screens(
        titleId = R.string.search,
        iconIdInactive = R.drawable.search,
        iconIdActive = R.drawable.search,
        route = "search_input",
        drawerSection = DrawerSection.PRIMARY
    )

    object ListenTogether : Screens(
        titleId = R.string.together,
        iconIdInactive = R.drawable.group_outlined,
        iconIdActive = R.drawable.group_filled,
        route = "listen_together"
    )

    object Library : Screens(
        titleId = R.string.filter_library,
        iconIdInactive = R.drawable.library_music_outlined,
        iconIdActive = R.drawable.library_music_filled,
        route = "library",
        drawerSection = DrawerSection.PRIMARY
    )

    object History : Screens(
        titleId = R.string.history,
        iconIdInactive = R.drawable.history,
        iconIdActive = R.drawable.history,
        route = "history",
        drawerSection = DrawerSection.SECONDARY
    )

    object Stats : Screens(
        titleId = R.string.stats,
        iconIdInactive = R.drawable.stats,
        iconIdActive = R.drawable.stats,
        route = "stats",
        drawerSection = DrawerSection.SECONDARY
    )

    object Settings : Screens(
        titleId = R.string.settings,
        iconIdInactive = R.drawable.settings,
        iconIdActive = R.drawable.settings,
        route = "settings",
        drawerSection = DrawerSection.SETTINGS
    )

    object About : Screens(
        titleId = R.string.about,
        iconIdInactive = R.drawable.info,
        iconIdActive = R.drawable.info,
        route = "settings/about",
        drawerSection = DrawerSection.SETTINGS
    )

    companion object {
        val MainScreens = listOf(Home, Search, ListenTogether, Library)
        val DrawerScreens = listOf(Home, Search, Library, History, Stats, Settings, About)
    }
}
