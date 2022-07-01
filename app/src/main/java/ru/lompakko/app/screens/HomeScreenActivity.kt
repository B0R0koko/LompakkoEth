package ru.lompakko.app.screens

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.recyclerview.widget.*
import ru.lompakko.app.R
import ru.lompakko.app.databinding.AccountDialogBinding
import ru.lompakko.app.screens.adapters.AccountAdapter
import ru.lompakko.app.databinding.ActivityHomeScreenBinding
import ru.lompakko.app.databinding.TransactionDialogBinding
import ru.lompakko.app.models.accountList
import ru.lompakko.app.models.transactionList
import ru.lompakko.app.screens.adapters.TransactionAdapter
import ru.lompakko.app.tasks.ethereum.EthereumClient


// HomeScreenActivity - main home activity
class HomeScreenActivity : BaseActivity(),
    TransactionAdapter.OnItemClickListener,
    AccountAdapter.OnItemClickListener {

    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var alertBuilder: AlertDialog.Builder

    private var accountAdapter = AccountAdapter(accountList, this)
    private var transactionAdapter = TransactionAdapter(transactionList, this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        alertBuilder = AlertDialog.Builder(this)
        binding.manageAccountsClickableTextView.setOnClickListener {
            manageAccountsOnClick()
        }

        startAccountRecyclerView()
        startTransactionRecyclerView()
    }

    override fun onTransactionClicked(position: Int) {
        showAlertDialog(position)
    }

    override fun onAccountClicked(position: Int) {
        showAccountDialog(position)
    }

    // Initialize RecyclerView and Dots ScrollBar
    private fun startAccountRecyclerView() {
        val accountLayoutManager = LinearLayoutManager(this@HomeScreenActivity, RecyclerView.HORIZONTAL, false)
        val snapHelper = LinearSnapHelper()
        with (binding.accountsRecyclerView) {
            layoutManager = accountLayoutManager
            adapter = accountAdapter
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun startTransactionRecyclerView() {
        val transactionLayoutManager = LinearLayoutManager(this@HomeScreenActivity, RecyclerView.VERTICAL, false)
        with (binding.transactionsRecyclerView) {
            layoutManager = transactionLayoutManager
            adapter = transactionAdapter
        }
    }

    private fun startEtherScanActivity() {
        val url = "https://etherscan.io/address/0x3f39dab4a6f78a1e7d86b6970308bf7736693f81"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun showAlertDialog(position: Int) {
        val binding = TransactionDialogBinding.inflate(layoutInflater)
        val dialog = alertBuilder.create()
        dialog.window?.attributes?.gravity = Gravity.BOTTOM
        val transactionAmountString = transactionList[position].amount.toString() + " ETH"
        binding.transacitonAmount.text = transactionAmountString
        binding.etherScanButton.setOnClickListener { startEtherScanActivity() }

        with(binding.sentToTextView) {
            if (transactionList[position].type == "IN") {
                text = getString(R.string.sentTransactionText)
            } else {
                text = getString(R.string.receivedTransactionText)
            }
        }

        dialog.setView(binding.root)
        dialog.show()
    }

    private fun showAccountDialog(position: Int) {
        val binding = AccountDialogBinding.inflate(layoutInflater)
        val dialog = alertBuilder.create()
        dialog.window?.attributes?.gravity = Gravity.BOTTOM
        binding.accountName.text = accountList[position].accountName
        binding.accountBalance.text = accountList[position].accountBalance.toString()
        dialog.setView(binding.root)
        dialog.show()
    }

    private fun manageAccountsOnClick() {
        Intent(this, ManageAccountsActivity::class.java).also {
            startActivity(it)
        }
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}