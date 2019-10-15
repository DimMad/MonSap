package test.techtest.moneysapling.data.source.local

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import test.techtest.moneysapling.data.Transaction
import test.techtest.moneysapling.data.Transactions
import test.techtest.moneysapling.data.source.AccountTransactionsDataSource
import test.techtest.moneysapling.data.source.util.JSONLoader


class AccountTransactionsLocalDataSource private constructor(
    private val json: JSONLoader
) : AccountTransactionsDataSource {

    companion object {
        private var instance: AccountTransactionsLocalDataSource? = null

        fun getInstance(json: JSONLoader): AccountTransactionsDataSource =
            instance ?: AccountTransactionsLocalDataSource(json).also { instance = it }
    }

    override suspend fun getAccountTransactions(accountId: Int): List<Transaction>? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val adapter: JsonAdapter<Transactions> = moshi.adapter(Transactions::class.java)

        val jsonString = json.loadTransactions(accountId)

        return adapter.fromJson(jsonString)?.transactions
    }

}