package test.techtest.moneysapling.util

import android.content.Context
import test.techtest.moneysapling.accounttransactions.AccountTransactionsViewModelFactory
import test.techtest.moneysapling.data.source.AccountTransactionsDataSource
import test.techtest.moneysapling.data.source.AccountTransactionsRepository
import test.techtest.moneysapling.data.source.UserAccountSummaryDataSource
import test.techtest.moneysapling.data.source.UserAccountSummaryRepository
import test.techtest.moneysapling.data.source.local.AccountTransactionsLocalDataSource
import test.techtest.moneysapling.data.source.local.UserAccountSummaryLocalDataSource
import test.techtest.moneysapling.data.source.util.JSONLoader
import test.techtest.moneysapling.useraccountsummary.UserAccountSummaryViewModelFactory

/**
 * Simple dependency injection class to provide types to Classes.
 *
 * This is a simple, lazy version.
 * TODO: upgrade to Dagger or Koin
 */
object Injection {

    private fun getUserAccountSummaryRepository(context: Context): UserAccountSummaryDataSource {
        return UserAccountSummaryRepository.getInstance(
            UserAccountSummaryLocalDataSource.getInstance(
                JSONLoader(context)
            )
        )
    }

    private fun getAccountTransactionsRepository(context: Context): AccountTransactionsDataSource {
        return AccountTransactionsRepository.getInstance(
            AccountTransactionsLocalDataSource.getInstance(
                JSONLoader(context)
            )
        )
    }

    fun provideUserAccountSummaryViewModel(context: Context): UserAccountSummaryViewModelFactory {
        val repository = getUserAccountSummaryRepository(context)
        return UserAccountSummaryViewModelFactory(repository)
    }

    fun provideAccountTransactionsViewModel(context: Context): AccountTransactionsViewModelFactory {
        val repository = getAccountTransactionsRepository(context)
        return AccountTransactionsViewModelFactory(repository)
    }
}