package test.techtest.moneysapling.data.source

import test.techtest.moneysapling.data.Transaction

/**
 * Interface shared by repositories and data sources.
 *
 * Currently return nullable types as errors, for simplicity.
 * TODO: Create a Result generic class that can handle errors and results in mainstreamed way.
 */
interface AccountTransactionsDataSource {

    suspend fun getAccountTrasactions(): List<Transaction>?

    // TODO: Add other data access functions

}