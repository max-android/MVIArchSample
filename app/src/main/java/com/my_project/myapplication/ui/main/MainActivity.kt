package com.my_project.myapplication.ui.main

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.my_project.myapplication.R
import com.my_project.myapplication.model.data_holder.LocalHolder
import com.my_project.myapplication.ui.common.CONST
import com.my_project.myapplication.ui.common.Router
import com.my_project.myapplication.ui.common.Screens
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val fragmentManager: FragmentManager = supportFragmentManager
    private  val localHolder: LocalHolder by inject ()
    private val router: Router  by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (fragmentManager.findFragmentById(R.id.main_container) == null) openScreen()
    }

    private fun openScreen() = if (localHolder.token() == CONST.DEFAULT_TOKEN) showLogin()  else showSearch()

    private fun showLogin(){
        router.createFragment(fragmentManager, Screens.LOGIN_FRAGMENT)
    }

    fun showSearch(){
        router.createFragment(fragmentManager, Screens.SEARCH_FRAGMENT)
    }
}
