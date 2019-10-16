package test.techtest.moneysapling.data.source

import test.techtest.moneysapling.data.Transaction

class MockTransactionsDataSource(var transactions: List<Transaction>) : AccountTransactionsDataSource{

    override suspend fun getAccountTransactions(accountId: Int): List<Transaction>? = transactions
}