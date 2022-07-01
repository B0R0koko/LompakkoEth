package ru.lompakko.app.screens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import ru.lompakko.app.R
import ru.lompakko.app.databinding.AccountBinding
import ru.lompakko.app.databinding.AddAccountBinding
import ru.lompakko.app.databinding.TransactionBinding
import ru.lompakko.app.models.Account
import ru.lompakko.app.models.Transaction
import ru.lompakko.app.models.accountList


class AccountAdapter(
    private val accountList: MutableList<Account>,
    private val listener: OnItemClickListener
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Create ViewHolder that contains each element added to RecyclerView with implemented onClick action
   inner class AccountViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        private val binding = AccountBinding.bind(view)

        init {
            view.setOnClickListener(this)
        }

        fun fillTemplate(account: Account) = with(binding) {
            accountNameTextView.text = account.accountName
            val accountBalanceText = account.accountBalance.toString() + " ETH"
            assetAmount.text = accountBalanceText
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            listener.onAccountClicked(position)
        }
    }

    // Implement another ViewHolder - which adds new Accounts
    class AddAccountViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = AddAccountBinding.bind(view)
    }

    // Additional methods that are required to be implemented
    // Function that loads templated into the memory
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ACCOUNT) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.account, parent, false)
            return AccountViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.add_account, parent, false)
            return AddAccountViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AccountViewHolder) {
            holder.fillTemplate(accountList[position])
        }
    }

    // Number of elements in accountList
    override fun getItemCount(): Int = accountList.size + 1

    // Overload viewType function to create custom logic for determining children's type
    override fun getItemViewType(position: Int): Int {
        return if (position == accountList.size) NEW_ACCOUNT else ACCOUNT
    }

    interface OnItemClickListener {
        fun onAccountClicked(position: Int)
    }

    companion object {
        const val ACCOUNT = 0
        const val NEW_ACCOUNT = 1
    }
}