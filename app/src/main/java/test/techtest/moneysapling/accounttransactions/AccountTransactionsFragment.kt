package test.techtest.moneysapling.accounttransactions


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import test.techtest.moneysapling.databinding.FragmentAccountTransactionsBinding
import test.techtest.moneysapling.util.Injection

/**
 * A simple [Fragment] subclass.
 */
class AccountTransactionsFragment : Fragment() {

    private val args: AccountTransactionsFragmentArgs by navArgs()

    private val transactionsViewModel: AccountTransactionsViewModel by viewModels {
        Injection.provideAccountTransactionsViewModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAccountTransactionsBinding.inflate(inflater, container, false)

        val summaryAdapter = AccountTransactionsAdapter()
        binding.accountTransactionsList.adapter = summaryAdapter
        subscribeUi(binding, summaryAdapter)

        transactionsViewModel.setTitle(args.institution)
        transactionsViewModel.setHeader(args.accountName, args.balance.toDouble())
        transactionsViewModel.getTransactions(args.accountId)

        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        return binding.root
    }

    private fun subscribeUi(
        binding: FragmentAccountTransactionsBinding,
        adapter: AccountTransactionsAdapter
    ) {
        transactionsViewModel.transactions.observe(viewLifecycleOwner) { transacations ->
            adapter.submitList(transacations)
        }
        transactionsViewModel.title.observe(viewLifecycleOwner) {
            binding.apply {
                title = it
                executePendingBindings()
            }
        }
        transactionsViewModel.accountName.observe(viewLifecycleOwner) {
            binding.apply {
                accountName = it
                executePendingBindings()
            }
        }
        transactionsViewModel.accountBalance.observe(viewLifecycleOwner) {
            binding.apply {
                balance = it
                executePendingBindings()
            }
        }
    }
}
