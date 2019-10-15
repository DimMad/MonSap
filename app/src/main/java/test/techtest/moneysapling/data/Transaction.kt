package test.techtest.moneysapling.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Transaction(
    @Json(name = "account_id")
    val accountId: Int,
    val amount: Double,
    @Json(name = "category_id")
    val categoryId: Int,
    val date: String,
    val description: String,
    val id: Int
)

@JsonClass(generateAdapter = true)
data class Transactions(
    @Json(name = "transactions")
    val transactions: List<Transaction>
)
