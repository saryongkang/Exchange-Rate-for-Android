package com.saryong.example.presentation.currencylist

import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import com.saryong.example.presentation.currencylist.item.Currency
import com.saryong.example.presentation.currencylist.item.DiffCallback

@BindingAdapter("currencyList")
fun setCurrencyList(recyclerView: RecyclerView, itemList: ObservableArrayList<Currency>) {
  val adapter = recyclerView.adapter as CurrencyListAdapter
  val diff = DiffUtil.calculateDiff(
    DiffCallback(adapter.itemList, itemList), true)
  adapter.itemList = itemList.toList()
  diff.dispatchUpdatesTo(adapter)
}
