package com.swipefwd.ui.home.wallet

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swipefwd.R
import com.swipefwd.databinding.ItemWalletHistoryBinding

class WalletHistoryListAdapter(val context: Context) :
    RecyclerView.Adapter<WalletHistoryListAdapter.YourTribeHolder>() {

    class YourTribeHolder(val binding: ItemWalletHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourTribeHolder =
        ItemWalletHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { YourTribeHolder(this) }

    override fun onBindViewHolder(holder: YourTribeHolder, position: Int) {
        holder.binding.apply {
            if (position % 2 == 0) {
                ivStatus.setImageResource(R.drawable.debited)
                txtStatus.text = "Withdrawal"
                txtMoney.text = "-₹74"
            } else {
                ivStatus.setImageResource(R.drawable.credited)
                txtStatus.text = "Deposit"
                txtMoney.text = "+₹74"
            }
        }
    }

    override fun getItemCount(): Int = 10

}
