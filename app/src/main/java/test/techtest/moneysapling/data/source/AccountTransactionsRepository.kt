package test.techtest.moneysapling.data.source

import test.techtest.moneysapling.data.Transaction

class AccountTransactionsRepository: AccountTransactionsDataSource {

    override suspend fun getAccountTrasactions(): List<Transaction>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}