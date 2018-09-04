package com.saryong.example.presentation.addcurrency

import android.support.v7.util.DiffUtil
import com.saryong.example.data.local.CurrencySetting

class CurrencySettingDiffCallback(
  private val oldList: List<CurrencySetting>,
  private val newList: List<CurrencySetting>
) : DiffUtil.Callback() {
  override fun getOldListSize(): Int = oldList.size
  
  override fun getNewListSize(): Int = newList.size
  
  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
    oldList[oldItemPosition].code == newList[newItemPosition].code
  
  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
    oldList[oldItemPosition].code == newList[newItemPosition].code
}