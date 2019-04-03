package com.my_project.myapplication.koin

import com.my_project.myapplication.middleware.info.InfoMiddleware
import com.my_project.myapplication.middleware.login.LoginMiddleware
import com.my_project.myapplication.model.data_holder.LocalHolder
import com.my_project.myapplication.model.database.provideDatabase
import com.my_project.myapplication.model.network.Network
import com.my_project.myapplication.model.repository.InfoRepository
import com.my_project.myapplication.model.repository.LoginRepository
import com.my_project.myapplication.presentation.InfoComponent
import com.my_project.myapplication.presentation.LoginComponent
import com.my_project.myapplication.reducer.info.InfoReducer
import com.my_project.myapplication.reducer.login.LoginReducer
import com.my_project.myapplication.ui.common.Router
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module


val localHolderModule: Module = module {
    single { LocalHolder(androidApplication()) }
}

val routerModule: Module = module {
    single { Router() }
}

val networkModule: Module = module {
    single { Network().provideApiService() }
}

val dbModule: Module = module {
    single { provideDatabase(androidApplication()) }
}

val repositoryModule: Module = module {
    factory { LoginRepository(get(), get()) }
    factory { InfoRepository(get()) }
}

val middlewareModule: Module = module {
    factory { LoginMiddleware(get()) }
    factory { InfoMiddleware(get()) }
}

val reducerModule: Module = module {
    factory { LoginReducer() }
    factory { InfoReducer() }
}

val componentModule: Module = module {
    single { LoginComponent(get(), get()) }
    single { InfoComponent(get(), get()) }
}
