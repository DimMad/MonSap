package test.techtest.moneysapling.data.source

import test.techtest.moneysapling.data.Account

class MockSummaryDataSource(var accounts: List<Account>) : UserAccountSummaryDataSource{

    override suspend fun getUserAccountSummary(): List<Account>? = accounts
}