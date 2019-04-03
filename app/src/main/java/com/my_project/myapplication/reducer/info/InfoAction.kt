package com.my_project.myapplication.reducer.info

import com.my_project.myapplication.model.entities.Car


sealed class InfoAction {
    class InfoSuccessAction(val data: List<Car>) : InfoAction()
    class InfoFailureAction(val error: Throwable) : InfoAction()
    object InfoLoadingAction : InfoAction()
}