package com.my_project.myapplication.presentation

import android.annotation.SuppressLint
import com.jakewharton.rxrelay2.BehaviorRelay
import com.my_project.myapplication.middleware.info.InfoMiddleware
import com.my_project.myapplication.model.data_holder.InfoBehaviorSubject
import com.my_project.myapplication.reducer.info.InfoReducer
import com.my_project.myapplication.ui.MviView
import com.my_project.myapplication.ui.info.InfoIntent
import com.my_project.myapplication.ui.info.InfoState
import io.reactivex.Observable


class InfoComponent(private val infoMiddleware: InfoMiddleware, private val infoReducer: InfoReducer) {

    private val state = BehaviorRelay.createDefault<InfoState>(InfoState())
    val subject = InfoBehaviorSubject()

    @SuppressLint("CheckResult")
    fun bind(view: MviView<InfoIntent, InfoState>): Observable<InfoState> {
        return infoMiddleware.bind(view.intent).scan(state.value) { state, action -> infoReducer.reduce(state, action) }
    }
}