package test.techtest.moneysapling.accounttransactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import test.techtest.moneysapling.data.source.AccountTransactionsDataSource

/**
 * Jetpack ViewModel Factory pattern class
 */
class AccountTransactionsViewModelFactory(
    private val repository: AccountTransactionsDataSource
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = AccountTransactionsViewModel(repository) as T
}