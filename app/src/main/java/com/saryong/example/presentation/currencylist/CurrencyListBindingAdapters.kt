package com.saryong.example.presentation.currencylist

import android.arch.lifecycle.LiveData
import android.databinding.BindingAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import com.saryong.example.presentation.currencylist.item.CurrencyItem
import com.saryong.example.presentation.currencylist.item.DiffCallback
import timber.log.Timber

@BindingAdapter("currencyList")
fun setCurrencyList(recyclerView: RecyclerView, itemList: LiveData<List<CurrencyItem>>) {
  itemList.value?.let { newItemList ->
    Timber.d(newItemList.toString())
    
    val adapter = recyclerView.adapter as CurrencyListAdapter
    val diff = DiffUtil.calculateDiff(
      DiffCallback(adapter.itemList, newItemList), true)
    
    adapter.itemList = newItemList.toList()
  
    diff.dispatchUpdatesTo(adapter)
  }
}
