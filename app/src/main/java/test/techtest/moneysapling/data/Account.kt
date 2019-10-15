package test.techtest.moneysapling.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Account Pojo (Kojo???)
 *
 * Annotations from Moshi JSON parser
 */
@JsonClass(generateAdapter = true)
data class Account(
    val id: Int,
    val name: String,
    val institution: String,
    val currency: String,
    @Json(name = "current_balance")
    val currentBalance: Double,
    @Json(name = "current_balance_in_base")
    val currentBalanceInBase: Double
)

/**
 * Utility class to make Moshi parsing easier
 */
@JsonClass(generateAdapter = true)
data class Accounts(
    @Json(name = "accounts")
    val accounts: List<Account>
)

