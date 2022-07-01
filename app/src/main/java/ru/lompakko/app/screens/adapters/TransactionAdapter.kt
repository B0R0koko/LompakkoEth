package ru.lompakko.app.screens.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.lompakko.app.R
import ru.lompakko.app.databinding.AccountBinding
import ru.lompakko.app.databinding.TransactionBinding
import ru.lompakko.app.models.Account
import ru.lompakko.app.models.Transaction


class TransactionAdapter(
    private val transactionList: MutableList<Transaction>,
    private val listener: OnItemClickListener
    ) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {


    inner class TransactionViewHolder(view: View):
        RecyclerView.ViewHolder(view),
        View.OnClickListener {

        private val binding = TransactionBinding.bind(view)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            listener.onTransactionClicked(position)
        }

        fun fillTemplate(transaction: Transaction) = with(binding) {
            blockchainNameTransaction.text = transaction.blockchain
            transactionTimePassed.text = transaction.timeStamp
            transactionAmount.text = transaction.amount.toString()
            transactionFiatAmount.text = transaction.fiatAmount

            if (transaction.type == "IN") {
                transactionAmount.setTextColor(Color.GREEN)
            } else {
                transactionAmount.setTextColor(Color.RED)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.fillTemplate(transactionList[position])
    }

    override fun getItemCount(): Int = transactionList.size

    interface OnItemClickListener {
        fun onTransactionClicked(position: Int)
    }

}