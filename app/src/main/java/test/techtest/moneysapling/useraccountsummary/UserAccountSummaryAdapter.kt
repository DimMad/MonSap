package test.techtest.moneysapling.useraccountsummary


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import test.techtest.moneysapling.data.Account
import test.techtest.moneysapling.databinding.ListItemSummaryBinding
import timber.log.Timber

class UserAccountSummaryAdapter :
    ListAdapter<Account, RecyclerView.ViewHolder>(AccountSummaryDiffCallback()) {

    class UserSummaryItemViewHolder(
        private val binding: ListItemSummaryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                Timber.d("Account summary item clicked...")
                // TODO: Code here to start transactions screen
            }
        }

        fun bind(item: Account) {
            binding.apply {
                account = item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserSummaryItemViewHolder(
            ListItemSummaryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val account = getItem(position)
        (holder as UserSummaryItemViewHolder).bind(account)
    }

}

private class AccountSummaryDiffCallback : DiffUtil.ItemCallback<Account>() {
    override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem.id == newItem.id
    }
}