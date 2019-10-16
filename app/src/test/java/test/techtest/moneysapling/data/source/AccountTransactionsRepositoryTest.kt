package test.techtest.moneysapling.data.source

import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import test.techtest.moneysapling.data.Transaction


@ExperimentalCoroutinesApi
class AccountTransactionsRepositoryTest {

    private val transaction1 = Transaction(1, 10.0, 1, "DATE1", "DESC1", 1)
    private val transaction2 = Transaction(1, 10.0, 1, "DATE2", "DESC1", 1)
    private val transaction3 = Transaction(1, 10.0, 1, "DATE3", "DESC1", 1)
    private val transactions = arrayListOf(transaction1, transaction2, transaction3)
    private val sortedTransactions = arrayListOf(transaction3, transaction2, transaction1)
    private lateinit var accountTransactionsLocalDataSource: MockTransactionsDataSource

    // Unit under test
    private lateinit var transactionsRepository: AccountTransactionsRepository

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        accountTransactionsLocalDataSource = MockTransactionsDataSource(transactions)
        transactionsRepository = AccountTransactionsRepository.getInstance(
            accountTransactionsLocalDataSource,
            Dispatchers.Unconfined
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Test getAccountTransactions with no data`() = runBlocking {
        accountTransactionsLocalDataSource = MockTransactionsDataSource(arrayListOf())
        transactionsRepository.resetRepoInstance()
        transactionsRepository = AccountTransactionsRepository.getInstance(
            accountTransactionsLocalDataSource,
            Dispatchers.Unconfined
        )
        val accounts = transactionsRepository.getAccountTransactions(0)
        Truth.assertThat(accounts).isEmpty()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Test getAccountTransactions returns proper order`() = runBlocking {
        transactionsRepository.resetRepoInstance()
        transactionsRepository = AccountTransactionsRepository.getInstance(
            accountTransactionsLocalDataSource,
            Dispatchers.Unconfined
        )
        val accounts = transactionsRepository.getAccountTransactions(0)
        Truth.assertThat(accounts).isEqualTo(sortedTransactions)
    }
}