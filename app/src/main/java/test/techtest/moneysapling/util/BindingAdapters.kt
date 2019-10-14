package test.techtest.moneysapling.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.NumberFormat

/*
 * Binding Adapter functions.
 * Used to adjust data before providing it to the xml layouts that use the Data Binding library.
 */

@BindingAdapter("balance")
fun bindAccountBalance(view: TextView, balance: Double) {
    // TODO: improve over price presentation.
    val formattedPrice = NumberFormat.getCurrencyInstance()
    view.text = formattedPrice.format(balance)
}