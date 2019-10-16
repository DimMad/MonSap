package test.techtest.moneysapling.useraccountsummary


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
import test.techtest.moneysapling.data.Account
import test.techtest.moneysapling.data.source.UserAccountSummaryRepository

/**
 * Unit tests for [UserAccountSummaryViewModel]
 */
@ExperimentalCoroutinesApi
class UserAccountSummaryViewModelTest {

    private val account1 = Account(1, "N1", "INST1", "CUR1", 10.0, 0.0)
    private val account2 = Account(2, "N2", "INST2", "CUR2", 20.0, 0.0)
    private val account3 = Account(3, "N3", "INST1", "CUR2", 30.0, 0.0)
    private val accounts = arrayListOf(account1, account2, account3)
    private val emptyAccounts = arrayListOf<Account>()

    // Our unit for this test
    private lateinit var userAccountSummaryViewModel: UserAccountSummaryViewModel

    private lateinit var repository: UserAccountSummaryRepository

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repository = mockkClass(UserAccountSummaryRepository::class, relaxed = true)
        userAccountSummaryViewModel = UserAccountSummaryViewModel(repository)
    }

    @Test
    fun `Test getSummary with empty data`() {
        coEvery { repository.getUserAccountSummary() } returns emptyAccounts
        userAccountSummaryViewModel.getSummary()

        assertThat(LiveDataTestUtil.getValue(userAccountSummaryViewModel.summary)).isEqualTo(
            emptyAccounts
        )
    }

    @Test
    fun `Test getSummary with proper data`() {
        coEvery { repository.getUserAccountSummary() } returns accounts
        userAccountSummaryViewModel.getSummary()

        assertThat(LiveDataTestUtil.getValue(userAccountSummaryViewModel.summary)).isEqualTo(
            accounts
        )

        assertThat(LiveDataTestUtil.getValue(userAccountSummaryViewModel.balanceTotal)).isEqualTo(60.0)
    }
}