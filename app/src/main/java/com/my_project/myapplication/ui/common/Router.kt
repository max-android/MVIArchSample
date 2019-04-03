package com.my_project.myapplication.ui.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.my_project.myapplication.R
import com.my_project.myapplication.ui.info.InfoFragment
import com.my_project.myapplication.ui.login.LoginFragment


class Router {
    private lateinit var screen: String

    fun createFragment(fragmentManager: FragmentManager, screenKey: String) = when (screenKey) {

        Screens.LOGIN_FRAGMENT -> {
            screen = Screens.LOGIN_FRAGMENT
            showScreen(fragmentManager, LoginFragment.newInstance(), false)
        }

        Screens.SEARCH_FRAGMENT -> {
            screen = Screens.SEARCH_FRAGMENT
            showScreen(fragmentManager, InfoFragment.newInstance(), false)
        }

        else -> throw Exception("unknown case")
    }

    private fun showScreen(fragmentManager: FragmentManager, fragment: Fragment, addToBackStack: Boolean) {
        fragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .apply { if (addToBackStack) addToBackStack(null) }
            .setCustomAnimations(
                R.animator.slide_in_left,
                R.animator.slide_in_right
            )
            .commitAllowingStateLoss()
    }
}