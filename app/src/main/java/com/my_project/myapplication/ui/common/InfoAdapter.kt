package com.my_project.myapplication.ui.common

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.my_project.myapplication.R
import com.my_project.myapplication.model.entities.Car
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class InfoAdapter : RecyclerView.Adapter<InfoAdapter.InfoHolder>() {

    private val clickSubject = PublishSubject.create<Car>()
    private val items: MutableList<Car> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return InfoHolder(view)
    }

    override fun onBindViewHolder(holder: InfoHolder, position: Int) {
        val car = items[position]
        holder.bind(car)
    }


    override fun getItemCount() = items.size

    fun setData(info: List<Car>) {
        items.clear()
        items.addAll(info)
    }

    fun updateData(info: List<Car>) {
        items.clear()
        items.addAll(info)
        notifyDataSetChanged()
    }

    val clickEvent: Observable<Car> = clickSubject

    inner class InfoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var itemText = itemView.findViewById(R.id.itemTextView) as TextView

        init {
            itemView.setOnClickListener { clickSubject.onNext(items[layoutPosition]) }
        }

        fun bind(car: Car) {
            itemText.text = car.model
        }
    }
}