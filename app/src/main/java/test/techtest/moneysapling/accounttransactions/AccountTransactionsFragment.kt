package test.techtest.moneysapling.accounttransactions


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import test.techtest.moneysapling.R

/**
 * A simple [Fragment] subclass.
 */
class AccountTransactionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_transactions, container, false)
    }


}
