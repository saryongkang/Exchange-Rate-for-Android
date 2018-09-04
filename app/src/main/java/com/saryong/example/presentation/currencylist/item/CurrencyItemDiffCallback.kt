package com.saryong.example.presentation.currencylist.item

import android.support.v7.util.DiffUtil

class CurrencyItemDiffCallback(
  private val oldList: List<CurrencyItem>,
  private val newList: List<CurrencyItem>
) : DiffUtil.Callback() {
  override fun getOldListSize(): Int = oldList.size
  
  override fun getNewListSize(): Int = newList.size
  
  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
    oldList[oldItemPosition].code == newList[newItemPosition].code
  
  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
    oldList[oldItemPosition].exchangedAmount == newList[newItemPosition].exchangedAmount
}