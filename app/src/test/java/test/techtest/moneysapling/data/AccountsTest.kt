package test.techtest.moneysapling.data

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AccountsTest {

    private lateinit var accounts: Accounts

    @Before
    fun setup() {
        accounts = Accounts(
            arrayListOf(
                Account(0, "N1", "INST1", "CUR1", 10.0, 0.0),
                Account(1, "N2", "INST2", "CUR2", 20.0, 0.0)
            )
        )
    }

    @Test
    fun `Test empty accounts`() {
        val accounts = Accounts(ArrayList())
        assertTrue(accounts.accounts.isEmpty())
    }

    @Test
    fun `Test accounts with entries`() {
        assertTrue(accounts.accounts.count() == 2)
    }
}