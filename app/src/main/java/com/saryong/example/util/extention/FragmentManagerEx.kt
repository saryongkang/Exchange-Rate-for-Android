package com.saryong.example.util.extention

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
  beginTransaction().func().commit()
}
