package test.techtest.moneysapling.data

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TransactionsTest {

    private lateinit var transactions: Transactions

    @Before
    fun setup() {
        transactions = Transactions(
            arrayListOf(
                Transaction(1, 10.0, 1, "DATE1", "DESC1", 1),
                Transaction(2, 20.0, 2, "DATE2", "DESC2", 1)
            )
        )
    }

    @Test
    fun `Test empty transactions`() {
        val transactions = Transactions(ArrayList())
        Assert.assertTrue(transactions.transactions.isEmpty())
    }

    @Test
    fun `Test transactions with entries`() {
        Assert.assertTrue(transactions.transactions.count() == 2)
    }
}
