package test.techtest.moneysapling.data.source

import test.techtest.moneysapling.data.Account


/**
 * Interface shared by repositories and data sources.
 *
 * Currently return nullable types as errors, for simplicity.
 * TODO: Create a Result generic class that can handle errors and results in mainstreamed way.
 */
interface UserAccountSummaryDataSource {

    suspend fun getUserAccountSummary(): List<Account>?

    // TODO: Add other data access functions
}