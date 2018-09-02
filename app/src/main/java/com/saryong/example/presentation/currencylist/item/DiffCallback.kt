package com.saryong.example.presentation.currencylist.item

import android.support.v7.util.DiffUtil
import com.saryong.example.presentation.currencylist.item.Currency

class DiffCallback(
  private val oldList: List<Currency>,
  private val newList: List<Currency>
) : DiffUtil.Callback() {
  override fun getOldListSize(): Int = oldList.size

  override fun getNewListSize(): Int = newList.size

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
    oldList[oldItemPosition].code == newList[newItemPosition].code

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
    oldList[oldItemPosition].exchangedAmount == newList[newItemPosition].exchangedAmount
}