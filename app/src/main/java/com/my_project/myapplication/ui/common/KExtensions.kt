package com.my_project.myapplication.ui.common

import android.support.v4.app.Fragment
import org.jetbrains.anko.contentView
import org.jetbrains.anko.design.snackbar



fun Fragment.showAuthorizationError(message: String) {
    activity!!.contentView?.let {
        snackbar(it, message)
    }
}