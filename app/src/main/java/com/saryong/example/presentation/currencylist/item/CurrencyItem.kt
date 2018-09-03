package com.saryong.example.presentation.currencylist.item

import org.threeten.bp.LocalDateTime

enum class UpdateStatus(val rawValue: Int) {
  LOADING(0),
  SUCCESS(1),
  FAILED(-1);
  
  companion object {
    fun valueOf(value: Int) =
      when (value) {
        0 -> LOADING
        1 -> SUCCESS
        else -> FAILED
      }
  }
}

data class Currency(
  val code: String,
  val name: String,
  val exchangedAmount: Double = 0.0,
  val updatedAt: LocalDateTime = LocalDateTime.now(),
  val status: UpdateStatus = UpdateStatus.LOADING
)

//class CurrencyItem(
//  private val currency: Currency
//) : BindableItem<ItemCurrencyBinding>(currency.hashCode().toLong()) {
//
//  override fun bind(viewBinding: ItemCurrencyBinding, position: Int) {
//    viewBinding.currency = currency
//  }
//
//  override fun getLayout(): Int = R.layout.item_currency
//}