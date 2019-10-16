package test.techtest.moneysapling.accounttransactions

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import test.techtest.moneysapling.MainActivity
import test.techtest.moneysapling.R
import java.text.NumberFormat
import java.util.*

@RunWith(AndroidJUnit4::class)
class AccountTransactionsFragmentTest {

    private val accountId = 1
    private val institution = "INST"
    private val accountName = "NAME"
    private val balance = 10.0f

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun transitionToFragment() {
        activityTestRule.activity.apply {
            runOnUiThread {
                val bundle = Bundle().apply {
                    putInt("accountId", accountId)
                    putString("institution", institution)
                    putString("accountName", accountName)
                    putFloat("balance", balance)
                }
                findNavController(R.id.fragment).navigate(R.id.accountTransactionsFragment, bundle)
            }
        }
    }

    @Test
    fun testHeaderAppearance() {
        onView(withId(R.id.tv_account_name)).check(matches(withText(accountName)))
        val formatter = NumberFormat.getCurrencyInstance()
        formatter.currency = Currency.getInstance("JPY")
        formatter.minimumFractionDigits = 0
        formatter.maximumFractionDigits = 2
        onView(withId(R.id.tv_account_balance)).check(matches(withText(formatter.format(balance))))

    }
}