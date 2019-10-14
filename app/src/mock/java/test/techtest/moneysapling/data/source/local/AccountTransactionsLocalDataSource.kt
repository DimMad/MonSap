package test.techtest.moneysapling.data.source.local

import test.techtest.moneysapling.data.Transaction
import test.techtest.moneysapling.data.source.AccountTransactionsDataSource


class AccountTransactionsLocalDataSource: AccountTransactionsDataSource {

    override suspend fun getAccountTrasactions(): List<Transaction>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}