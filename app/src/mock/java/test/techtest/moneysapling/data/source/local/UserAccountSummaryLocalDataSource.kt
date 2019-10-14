package test.techtest.moneysapling.data.source.local


import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import test.techtest.moneysapling.data.Account
import test.techtest.moneysapling.data.Accounts
import test.techtest.moneysapling.data.source.UserAccountSummaryDataSource
import test.techtest.moneysapling.data.source.util.JSONLoader


/**
 * Mock concrete implementation of the local data source.
 *
 * Uses the JSON files from the Assets folder to load data in memory.
 */
class UserAccountSummaryLocalDataSource private constructor(
    private val json: JSONLoader
) : UserAccountSummaryDataSource {

    companion object {
        private var instance: UserAccountSummaryLocalDataSource? = null

        fun getInstance(json: JSONLoader): UserAccountSummaryDataSource =
            instance ?: UserAccountSummaryLocalDataSource(json).also { instance = it }
    }

    override suspend fun getUserAccountSummary(): List<Account>? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val adapter: JsonAdapter<Accounts> = moshi.adapter(Accounts::class.java)

        val jsonString = json.loadAccounts()

        return adapter.fromJson(jsonString)?.accounts
    }

}