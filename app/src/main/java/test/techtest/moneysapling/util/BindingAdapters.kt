package test.techtest.moneysapling.util

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

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