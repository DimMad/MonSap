package test.techtest.moneysapling.accounttransactions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import test.techtest.moneysapling.LiveDataTestUtil
import test.techtest.moneysapling.MainCoroutineRule
import test.techtest.moneysapling.data.Transaction
import test.techtest.moneysapling.data.source.AccountTransactionsRepository

/**
 * Unit tests for [AccountTransactionsViewModel]
 */
@ExperimentalCoroutinesApi
class AccountTransactionsViewModelTest {

    private val transaction1 = Transaction(1, 10.0, 1, "DATE1", "DESC1", 1)
    private val transaction2 = Transaction(2, 20.0, 2, "DATE2", "DESC2", 1)
    private val transactions = arrayListOf(transaction1, transaction2)
    private val emptyTransactions = arrayListOf<Transaction>()

    // Out unit for this test
    private lateinit var accountTransactionsViewModel: AccountTransactionsViewModel

    private lateinit var repository: AccountTransactionsRepository

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repository = mockkClass(AccountTransactionsRepository::class, relaxed = true)
        accountTransactionsViewModel = AccountTransactionsViewModel(repository)
    }

    @Test
    fun `Test setHeader`() {
        accountTransactionsViewModel.setHeader("INST", "NAME", 0.0)
        assertThat(LiveDataTestUtil.getValue(accountTransactionsViewModel.title)).isEqualTo("INST")
        assertThat(LiveDataTestUtil.getValue(accountTransactionsViewModel.accountName)).isEqualTo("NAME")
        assertThat(LiveDataTestUtil.getValue(accountTransactionsViewModel.accountBalance)).isEqualTo(0.0)
    }

    @Test
    fun `Test getTransactions with empty data`() {
        coEvery { repository.getAccountTransactions(0) } returns emptyTransactions
        accountTransactionsViewModel.getTransactions(0)

        assertThat(LiveDataTestUtil.getValue(accountTransactionsViewModel.transactions)).isEqualTo(
            emptyTransactions
        )
    }

    @Test
    fun `Test getTransactions with proper data`() {
        coEvery { repository.getAccountTransactions(0) } returns transactions
        accountTransactionsViewModel.getTransactions(0)

        assertThat(LiveDataTestUtil.getValue(accountTransactionsViewModel.transactions)).isEqualTo(
            transactions
        )
    }
}