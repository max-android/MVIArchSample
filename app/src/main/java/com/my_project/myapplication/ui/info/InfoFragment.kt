package com.my_project.myapplication.ui.info

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.jakewharton.rxbinding2.view.clicks
import com.my_project.myapplication.R
import com.my_project.myapplication.presentation.InfoComponent
import com.my_project.myapplication.ui.MviView
import com.my_project.myapplication.ui.common.InfoAdapter
import com.my_project.myapplication.ui.common.showAuthorizationError
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.info_fragment.*
import org.jetbrains.anko.support.v4.longToast
import org.koin.android.ext.android.inject


class InfoFragment : Fragment(), MviView<InfoIntent, InfoState> {

    companion object {
        fun newInstance(): InfoFragment {
            return InfoFragment()
        }
    }

    private val infoComponent: InfoComponent by inject()
    private val subscriptions = CompositeDisposable()
    override lateinit var intent: Observable<InfoIntent>
    private lateinit var infoAdapter: InfoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        observeSignInButtonClicks()
        subscribe()
    }

    override fun onDestroy() {
        subscriptions.dispose()
        super.onDestroy()
    }

    override fun render(state: InfoState) {

        infoProgressBar.visibility = if (state.loading) View.VISIBLE else View.GONE

        state.data?.let {
            infoAdapter.setData(it)
            infoComponent.subject.setIntoSubject(it)
        }

        state.error?.let {
            showAuthorizationError(it.message!!)
        }
    }

    private fun initAdapter() {
        infoAdapter = InfoAdapter()
        infoRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@InfoFragment.context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@InfoFragment.context, LinearLayout.VERTICAL))
            adapter = infoAdapter
        }
    }

    @SuppressLint("RxSubscribeOnError", "CheckResult")
    private fun observeSignInButtonClicks() {
        intent = infoButton.clicks().map { InfoIntent() }
    }

    private fun subscribe() {
        subscriptions.add(infoComponent.bind(this).subscribe(::render))
        subscriptions.add(infoAdapter.clickEvent.subscribe { longToast("Clicked on ${it.model}") })
        subscriptions.add(infoComponent.subject.getFromSubject().subscribe({
            if (infoAdapter.itemCount == 0) infoAdapter.setData(it)
        }, {
            longToast("Error: ${it.message}")
        }))
    }
}