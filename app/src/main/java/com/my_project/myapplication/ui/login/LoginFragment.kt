package com.my_project.myapplication.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.textChanges
import com.my_project.myapplication.R
import com.my_project.myapplication.presentation.LoginComponent
import com.my_project.myapplication.ui.MviView
import com.my_project.myapplication.ui.common.showAuthorizationError
import com.my_project.myapplication.ui.main.MainActivity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.android.ext.android.inject


class LoginFragment : Fragment(), MviView<LoginIntent, LoginState> {

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    private val loginComponent: LoginComponent by inject()
    private val subscriptions = CompositeDisposable()
    override lateinit var intent: Observable<LoginIntent>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        observeSignInButtonClicks()
        observeSignInButtonState()
        subscribe()
    }

    override fun onDestroy() {
        saveData()
        subscriptions.dispose()
        super.onDestroy()
    }

    override fun render(state: LoginState) {

        state.data?.let {
            (activity as MainActivity).showSearch()
        }

        state.error?.let {
            showAuthorizationError(it.message!!)
            (activity as MainActivity).showSearch()
        }
    }

    @SuppressLint("RxSubscribeOnError", "CheckResult")
    private fun observeSignInButtonClicks() {
        intent = signInButton.clicks()
            //.debounce(3L, TimeUnit.SECONDS)
            .map { LoginIntent(loginEditText.text.toString(), passwordEditText.text.toString()) }
    }

    @SuppressLint("RxSubscribeOnError")
    private fun observeSignInButtonState() {
        subscriptions.add(
            Observables.combineLatest(loginEditText.textChanges(), passwordEditText.textChanges()) { login, password ->
                login.length >= 6 && login.isNotBlank() && password.length >= 4 && password.isNotBlank()
            }.subscribe { signInButtonEnabled ->
                signInButton.isEnabled = signInButtonEnabled
            }
        )
    }

    private fun subscribe() {
        subscriptions.add(loginComponent.bind(this).subscribe(::render))
    }

    private fun initData() {
        loginComponent.subject.getFromSubject().value?.let {
            loginEditText.setText(it.first)
            passwordEditText.setText(it.second)
        }
    }

    private fun saveData() {
        if (!loginEditText?.text.isNullOrEmpty() && !passwordEditText?.text.isNullOrEmpty()) {
            loginComponent.subject.setIntoSubject(
                (Pair(
                    loginEditText.text.toString(),
                    passwordEditText.text.toString()
                ))
            )
        }
    }
}