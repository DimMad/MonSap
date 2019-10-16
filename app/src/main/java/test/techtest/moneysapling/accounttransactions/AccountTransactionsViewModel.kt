package test.techtest.moneysapling.accounttransactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import test.techtest.moneysapling.data.Transaction
import test.techtest.moneysapling.data.source.AccountTransactionsDataSource

/**
 * The ViewModel used in [AccountTransactionsFragment]
 */
class AccountTransactionsViewModel(private val repository: AccountTransactionsDataSource) :
    ViewModel() {

    private val transactionsState = MutableLiveData<List<Transaction>>()
    val transactions: LiveData<List<Transaction>>
        get() = transactionsState

    private val titleState = MutableLiveData<String>()
    val title: LiveData<String>
        get() = titleState

    private val accountNameState = MutableLiveData<String>()
    val accountName: LiveData<String>
        get() = accountNameState

    private val accountBalanceState = MutableLiveData<Double>()
    val accountBalance: LiveData<Double>
        get() = accountBalanceState

    // TODO: use the commented property to build logic that stops the Repository to be called each time.
//    private val isDataAvailable = MutableLiveData<Boolean>()

    fun setHeader(institution: String, name: String, balance: Double) {
        titleState.value = institution
        accountNameState.value = name
        accountBalanceState.value = balance
    }

    fun getTransactions(accountId: Int) {
        viewModelScope.launch {
            transactionsState.value = repository.getAccountTransactions(accountId)
        }
    }
}