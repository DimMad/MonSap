package test.techtest.moneysapling.accounttransactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import test.techtest.moneysapling.data.Transaction
import test.techtest.moneysapling.databinding.ListItemTransactionsBinding


/**
 * [RecyclerView] [ListAdapter] class for Transactions screen.
 * Gets the formatted data and place them in the list.
 */
class AccountTransactionsAdapter :
    ListAdapter<Transaction, RecyclerView.ViewHolder>(AccountTransactionsDiffCallback()) {

    class AccountTransactionsItemViewHolder(
        private val binding: ListItemTransactionsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Transaction) {
            binding.apply {
                transaction = item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AccountTransactionsItemViewHolder(
            ListItemTransactionsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val account = getItem(position)
        (holder as AccountTransactionsItemViewHolder).bind(account)
    }

}

/**
 * Utility class inheriting the Recycler'S [DiffUtil] to add/remove items from the list.
 */
private class AccountTransactionsDiffCallback : DiffUtil.ItemCallback<Transaction>() {
    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.id == newItem.id
    }
}