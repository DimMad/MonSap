package test.techtest.moneysapling.useraccountsummary


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import test.techtest.moneysapling.databinding.FragmentUserAccountSummaryBinding
import test.techtest.moneysapling.util.Injection

/**
 * A simple [Fragment] subclass.
 */
class UserAccountSummaryFragment : Fragment() {

    private val summaryViewModel: UserAccountSummaryViewModel by viewModels {
        Injection.provideUserAccountSummaryViewModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentUserAccountSummaryBinding.inflate(inflater, container, false)

        val summaryAdapter = UserAccountSummaryAdapter()
        binding.accountSummaryList.adapter = summaryAdapter
        subscribeUi(binding, summaryAdapter)

        return binding.root
    }

    private fun subscribeUi(binding: FragmentUserAccountSummaryBinding, adapter: UserAccountSummaryAdapter) {
        summaryViewModel.summary.observe(viewLifecycleOwner) { summary ->
            adapter.submitList(summary)
        }
        summaryViewModel.balanceTotal.observe(viewLifecycleOwner) {
            binding.apply {
                balanceTotal = it
                executePendingBindings()
            }
        }
    }
}
