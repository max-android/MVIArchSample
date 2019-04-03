package com.my_project.myapplication.reducer.info

import com.my_project.myapplication.reducer.Reducer
import com.my_project.myapplication.ui.info.InfoState


class InfoReducer : Reducer<InfoState, InfoAction> {
    override fun reduce(state: InfoState, action: InfoAction): InfoState {
        return when (action) {
            is InfoAction.InfoLoadingAction -> state.copy(loading = true)

            is InfoAction.InfoSuccessAction -> state.copy(
                loading = false,
                data = action.data
            )

            is InfoAction.InfoFailureAction -> state.copy(
                loading = false,
                error = action.error
            )
        }
    }
}