package test.techtest.moneysapling.data.source

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import test.techtest.moneysapling.data.Transaction


/**
 * Concrete repository implementation for the [Transaction]s.
 *
 * This is a very basic version that does the bare minimum.
 * TODO: Add some kind of caching and logic for syncing remote and local data sources.
 */
class AccountTransactionsRepository private constructor(
    private val accountTransactionsLocalDataSource: AccountTransactionsDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : AccountTransactionsDataSource {

    // Making a singleton so only one instance ever exists
    companion object {
        private var instance: AccountTransactionsRepository? = null

        fun getInstance(
            accountTransactionsLocalDataSource: AccountTransactionsDataSource,
            ioDispatcher: CoroutineDispatcher = Dispatchers.IO
        ) =
            instance ?: AccountTransactionsRepository(
                accountTransactionsLocalDataSource,
                ioDispatcher
            ).also { instance = it }
    }

    override suspend fun getAccountTransactions(accountId: Int): List<Transaction>? =
        withContext(ioDispatcher) {
            // TODO: Naive comparison using the provided strings. Should change to proper date handling.
            return@withContext accountTransactionsLocalDataSource.getAccountTransactions(accountId)
                ?.sortedWith(compareByDescending { it.date })
        }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun resetRepoInstance() {
        instance = null
    }
}