package test.techtest.moneysapling.useraccountsummary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import test.techtest.moneysapling.data.Account
import test.techtest.moneysapling.data.source.UserAccountSummaryDataSource

/**
 * The ViewModel used in [UserAccountSummaryFragment]
 */
class UserAccountSummaryViewModel(private val repository: UserAccountSummaryDataSource) :
    ViewModel() {

    private val summaryState = MutableLiveData<List<Account>>()
    val summary: LiveData<List<Account>>
        get() = summaryState

    private val balanceTotalState = MutableLiveData<Double>(0.0)
    val balanceTotal: LiveData<Double>
        get() = balanceTotalState

    // A warning is shown here. Not fixing as code that could use this is missing.
    // If indeed is not user from outside then can be private
    fun getSummary() {
        viewModelScope.launch {
            // FIXME: A bug here with the balance when rotating the screen
            val accounts = repository.getUserAccountSummary()
            summaryState.value = accounts
            var sum = 0.0
            for (account in accounts!!) {
               sum += account.currentBalance
            }
            balanceTotalState.value = sum
        }
    }
}