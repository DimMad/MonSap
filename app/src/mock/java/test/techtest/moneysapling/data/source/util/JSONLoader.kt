package test.techtest.moneysapling.data.source.util

import android.content.Context

class JSONLoader(val context: Context) {

    fun loadAccounts(): String {
        return context.assets.open("accounts.json").bufferedReader().use { it.readText() }
    }

    fun loadTransactions(accountId: Int): String {
        return context.assets.open("transactions_$accountId.json").bufferedReader().use { it.readText() }
    }
}