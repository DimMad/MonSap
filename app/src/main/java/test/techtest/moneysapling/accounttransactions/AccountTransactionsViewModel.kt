package test.techtest.moneysapling.accounttransactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import test.techtest.moneysapling.data.Transaction
import test.techtest.moneysapling.data.source.AccountTransactionsDataSource

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

    private val isDataAvailable = MutableLiveData<Boolean>()

    fun setTitle(title: String) {
        titleState.value = title
    }

    fun setHeader(name: String, balance: Double) {
        accountNameState.value = name
        accountBalanceState.value = balance
    }

    fun getTransactions(accountId: Int) {
        viewModelScope.launch {
            transactionsState.value = repository.getAccountTransactions(accountId)
        }
    }
}