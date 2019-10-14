package test.techtest.moneysapling.accounttransactions


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import test.techtest.moneysapling.databinding.FragmentAccountTransactionsBinding

/**
 * A simple [Fragment] subclass.
 */
class AccountTransactionsFragment : Fragment() {

    private val args: AccountTransactionsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAccountTransactionsBinding.inflate(inflater, container, false)



        return binding.root
    }


}
