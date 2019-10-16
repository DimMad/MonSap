package test.techtest.moneysapling.data.source

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import test.techtest.moneysapling.data.Account


@ExperimentalCoroutinesApi
class UserAccountSummaryRepositoryTest {

    private val account1 = Account(1, "N1", "INST1", "CUR1", 10.0, 0.0)
    private val account2 = Account(2, "N2", "INST2", "CUR2", 20.0, 0.0)
    private val account3 = Account(3, "N3", "INST1", "CUR2", 30.0, 0.0)
    private val accounts = arrayListOf(account1, account2, account3)
    private val sortedAccounts = arrayListOf(account1, account3, account2)
    private lateinit var userAccountSummaryLocalDataSource: MockSummaryDataSource

    // Unit under test
    private lateinit var summaryRepository: UserAccountSummaryRepository

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        userAccountSummaryLocalDataSource = MockSummaryDataSource(accounts)
        summaryRepository = UserAccountSummaryRepository.getInstance(
            userAccountSummaryLocalDataSource,
            Dispatchers.Unconfined
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Test getUserAccountSummary with no data`() = runBlocking {
        userAccountSummaryLocalDataSource = MockSummaryDataSource(arrayListOf())
        summaryRepository.resetRepoInstance()
        summaryRepository = UserAccountSummaryRepository.getInstance(
            userAccountSummaryLocalDataSource,
            Dispatchers.Unconfined
        )
        val accounts = summaryRepository.getUserAccountSummary()
        assertThat(accounts).isEmpty()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Test getUserAccountSummary returns proper order`() = runBlocking {
        summaryRepository.resetRepoInstance()
        summaryRepository = UserAccountSummaryRepository.getInstance(
            userAccountSummaryLocalDataSource,
            Dispatchers.Unconfined
        )
        val accounts = summaryRepository.getUserAccountSummary()
        assertThat(accounts).isEqualTo(sortedAccounts)
    }
}