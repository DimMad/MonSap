package test.techtest.moneysapling.useraccountsummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import test.techtest.moneysapling.data.source.UserAccountSummaryDataSource

class UserAccountSummaryViewModelFactory(
    private val repository: UserAccountSummaryDataSource
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = UserAccountSummaryViewModel(repository) as T
}