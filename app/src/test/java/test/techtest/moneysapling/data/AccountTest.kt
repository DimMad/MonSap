package test.techtest.moneysapling.data

import org.junit.Before

class AccountTest {

    private lateinit var account: Account

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
}