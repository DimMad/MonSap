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

    // Load data in init
    // TODO: needs some code for refreshing
    init {
        getSummary()
    }

    fun getSummary() {
        viewModelScope.launch {
            summaryState.value = repository.getUserAccountSummary()
            for (balance in summary.value!!) {
                balanceTotalState.value = balanceTotalState.value?.plus(balance.currentBalance)
            }
        }
    }
}