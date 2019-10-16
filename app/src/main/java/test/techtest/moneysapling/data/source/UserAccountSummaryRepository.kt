package test.techtest.moneysapling.data.source

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import test.techtest.moneysapling.data.Account


/**
 * Concrete implementation of the repository handling data for the account summary feature.
 *
 * TODO: Depending on the needs, caching and/or local and remote syncing can be done here
 */
class UserAccountSummaryRepository private constructor(
    private val userAccountSummaryLocalDataSource: UserAccountSummaryDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : UserAccountSummaryDataSource {

    companion object {
        private var instance: UserAccountSummaryRepository? = null

        fun getInstance(userAccountSummaryLocalDataSource: UserAccountSummaryDataSource, ioDispatcher: CoroutineDispatcher = Dispatchers.IO) =
            instance ?: UserAccountSummaryRepository(userAccountSummaryLocalDataSource, ioDispatcher).also { instance = it }
    }

    override suspend fun getUserAccountSummary(): List<Account>? = withContext(ioDispatcher) {
        // Uses the magic of Koltin to sort the data fist by name and then by institution
        // effectively grouping them (user story requirement)
        return@withContext userAccountSummaryLocalDataSource.getUserAccountSummary()?.sortedWith(compareBy({ it.institution }, { it.name }))
    }


    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun resetRepoInstance() {
        instance = null
    }
}