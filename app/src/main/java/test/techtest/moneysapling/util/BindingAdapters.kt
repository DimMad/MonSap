package test.techtest.moneysapling.util

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

/*
 * Binding Adapter functions.
 * Used to adjust data before providing it to the xml layouts that use the Data Binding library.
 */

@BindingAdapter(value = ["balance", "currency"])
fun bindAccountBalance(view: TextView, balance: Double, currency: String) {
    // TODO: improve over price presentation. Only the summary list shows the correct currency.
    val formatter = NumberFormat.getCurrencyInstance()
    formatter.currency = Currency.getInstance(currency)
    formatter.minimumFractionDigits = 0
    formatter.maximumFractionDigits = 2
    view.text = formatter.format(balance)
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("date")
fun bindDate(view: TextView, dateStr: String) {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
    val formatter = SimpleDateFormat("yyyy/MM/dd")
    val parsed = parser.parse(dateStr)
    if (parsed != null) {
        view.text = formatter.format(parsed)
    } else {
        view.text = ""
    }
}

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}