package test.techtest.moneysapling.data.source

import test.techtest.moneysapling.data.Account


/**
 * Concrete implementation of the repository handling data for the account summary feature.
 *
 * TODO: Depending on the needs, caching and/or local and remote syncing can be done here
 */
class UserAccountSummaryRepository private constructor(
    private val userAccountSummaryLocalDataSource: UserAccountSummaryDataSource
) : UserAccountSummaryDataSource {

    companion object {
        private var instance: UserAccountSummaryRepository? = null

        fun getInstance(userAccountSummaryLocalDataSource: UserAccountSummaryDataSource) =
            instance ?: UserAccountSummaryRepository(userAccountSummaryLocalDataSource).also { instance = it }
    }

    override suspend fun getUserAccountSummary(): List<Account>? {
        // Uses the magic of Koltin to sort the data fist by name and then by institution
        // effectively grouping them (user story requirement)
        return userAccountSummaryLocalDataSource.getUserAccountSummary()?.sortedWith(compareBy({ it.name }, { it.institution }))
    }

}