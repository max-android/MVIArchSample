package com.my_project.myapplication.middleware.info

import com.my_project.myapplication.middleware.Middleware
import com.my_project.myapplication.model.entities.Car
import com.my_project.myapplication.model.repository.InfoRepository
import com.my_project.myapplication.reducer.info.InfoAction
import com.my_project.myapplication.ui.common.CONST
import com.my_project.myapplication.ui.info.InfoIntent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class InfoMiddleware(private val infoRepository: InfoRepository) : Middleware<InfoIntent, InfoAction> {
    override fun bind(intents: Observable<InfoIntent>): Observable<InfoAction> {
        //имитация сложного запроса посредством заменой на бд для примера
        return intents.flatMap {
            infoRepository.initData()
                .subscribeOn(Schedulers.io())
                .andThen(
                    infoRepository.requestAllPersons()
                        .subscribeOn(Schedulers.io())
                        .toObservable()
                        .concatMap { Observable.fromIterable(it).subscribeOn(Schedulers.computation()) }
                        .concatMap {
                            Observable.just(infoRepository.requestCar(it.type)).subscribeOn(Schedulers.computation())
                        }
                        .buffer(CONST.BUFFER) { ArrayList<Car>() }
                        .map<InfoAction> { list -> InfoAction.InfoSuccessAction(list) }
                        .onErrorReturn { e -> InfoAction.InfoFailureAction(e) }
                        .startWith(InfoAction.InfoLoadingAction)
                        .observeOn(AndroidSchedulers.mainThread())
                )
        }
    }
}